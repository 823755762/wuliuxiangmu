package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.VehicleMapper;
import com.hz.pojo.DouArea;
import com.hz.pojo.Goods;
import com.hz.pojo.Orderss;
import com.hz.pojo.Vehicle;
import com.hz.service.DouAreaService;
import com.hz.service.GoodsService;
import com.hz.service.OrderssService;
import com.hz.service.VehicleService;
import com.hz.utils.JsonMassage;
import com.hz.utils.LocationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author UserG
 * @since 2022-04-26
 */
@RestController
@RequestMapping("/orderss")
public class OrderssController {
    @Autowired
    private OrderssService orderssService;
    @Autowired
    private DouAreaService douAreaService;
    @Autowired
    private LocationUtils locationUtils;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleMapper vehicleMapper;


    @RequestMapping("/orderssList")
    @ResponseBody
    public JsonMassage<List<Orderss>> orderssList(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            Long orderState,
            Long waybillId,
            String start_time,
            String end_time
    ) {
        QueryWrapper<Orderss> queryWrap = new QueryWrapper<>();

        if (orderState != null) {
            queryWrap.eq("order_state", orderState);
        }
        if (waybillId != null) {
            queryWrap.like("waybill_id", waybillId);
        }
        if (start_time != null) {
            queryWrap.apply("date_format(create_time,'%Y-%m-%d') >= date_format( '" + start_time + " ','%Y-%m-%d')");
        }
        if (end_time != null) {
            queryWrap.apply("date_format(create_time,'%Y-%m-%d') <= date_format( '" + end_time + " ','%Y-%m-%d')");
        }
        queryWrap.orderByDesc("create_time");

        Page<Orderss> page = new Page<>(pageNo, pageSize);
        Page<Orderss> orderssPage = orderssService.page(page, queryWrap);
        JsonMassage<List<Orderss>> JsonMas = new JsonMassage<List<Orderss>>(200, "ok", Math.toIntExact(page.getTotal()), orderssPage.getRecords());
        return JsonMas;
    }

    @RequestMapping("/saveOrderss")
    public JsonMassage<Boolean> saveOrderss(
            Orderss orderss,
            @RequestParam(value = "origin[]", required = false) String[] origin,
            @RequestParam(value = "destination[]", required = false) String[] destination,
            String addressA,
            String addressB,
            Long goodsType,
            String weight,
            String volume,
            Integer storageStatus
    ) {
        //生成订单编号    18位   2022 0505 1520 55xx xx
        String waybillId = "";
        //获取时间戳+4位随机数
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        waybillId = df.format(new Date()) + (int) ((Math.random() * 9 + 1) * 1000);//2022 0505 1539 3361 17
        orderss.setWaybillId(Long.valueOf(waybillId));
        orderss.setOrderAllAmount(orderss.getPrepaymentAmount());
        //获取付款总金额 计算剩余付款金额
        BigDecimal orderEstimatedAmount = orderss.getOrderEstimatedAmount();
        BigDecimal orderAllAmount = orderss.getOrderAllAmount();
        BigDecimal subtract = orderEstimatedAmount.subtract(orderAllAmount);

        orderss.setRemainingAmount(subtract);
        if (origin != null && destination != null) {
            //获取始发地地址
            String orderFromTitle = "";
            for (int i = 0; i < origin.length; i++) {
                QueryWrapper<DouArea> queryWrap = new QueryWrapper<DouArea>();
                queryWrap.eq("area_id", origin[i]);
                orderFromTitle += douAreaService.getOne(queryWrap).getName();
            }
            if (addressA != null) {
                orderFromTitle += addressA;
            }
            //获取目的地地址
            String orderFinishTitle = "";
            for (int i = 0; i < destination.length; i++) {
                QueryWrapper<DouArea> queryWrap = new QueryWrapper<DouArea>();
                queryWrap.eq("area_id", destination[i]);
                orderFinishTitle += douAreaService.getOne(queryWrap).getName();
            }
            if (addressB != null) {
                orderFinishTitle += addressB;
            }
            //对对象进行赋值
            orderss.setOrderFromTitle(orderFromTitle);
            orderss.setOrderFinishTitle(orderFinishTitle);
            //正地理编码服务
            //获取始发地 X/Y值
            Map<String, Double> stringDoubleMap = locationUtils.AddressTolongitudea(orderFromTitle);

            //  X表示纬度34.62470471302437  Y表示经度112.47046928828706
            if (stringDoubleMap != null) {
                orderss.setOrderFromX(String.valueOf(stringDoubleMap.get("lat")));
                orderss.setOrderFromY(String.valueOf(stringDoubleMap.get("lng")));
            }

            Map<String, Double> stringDoubleMap1 = locationUtils.AddressTolongitudea(orderFinishTitle);
            //  X表示纬度34.62470471302437  Y表示经度112.47046928828706
            if (stringDoubleMap != null) {
                orderss.setOrderFinishX(String.valueOf(stringDoubleMap1.get("lat")));
                orderss.setOrderFinishY(String.valueOf(stringDoubleMap1.get("lng")));
            }
        }
        boolean save = orderssService.save(orderss);

        QueryWrapper<Orderss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("waybill_id", waybillId);
        Orderss one = orderssService.getOne(queryWrapper);

        Goods goods = new Goods();
        //生成货物编号
        String goodsNumber = "LYHZ" + df.format(new Date());

        goods.setGoodsNumber(goodsNumber);
        goods.setGoodsValue(weight);
        goods.setGoodsVolume(volume);
        goods.setGoodsTypeId(goodsType);
        goods.setGoodsIsStorage(storageStatus);
        goods.setOrderId(one.getOrderId());
        goodsService.save(goods);


        JsonMassage<Boolean> jsonMassage = new JsonMassage<Boolean>(200, "ok", null, save);


        return jsonMassage;

    }

    @RequestMapping("/getById")
    public JsonMassage<Orderss> getById(Integer orderId) {
        Orderss orderss = orderssService.getById(orderId);
        JsonMassage<Orderss> jsonMassage = new JsonMassage<Orderss>(200, "ok", null, orderss);
        return jsonMassage;

    }


    @RequestMapping("/updateById")
    public JsonMassage<Orderss> updateById(Orderss orderss) {
        //获取付款总金额 计算剩余付款金额
        BigDecimal orderEstimatedAmount = orderss.getOrderActualAmount();
        BigDecimal orderAllAmount = orderss.getOrderAllAmount();
        BigDecimal subtract = orderEstimatedAmount.subtract(orderAllAmount);
        orderss.setRemainingAmount(subtract);
        int i = orderss.getOrderAllAmount().compareTo(orderss.getOrderActualAmount());
        if(i==1&&orderss.getOrderState()==3||i==1&&orderss.getOrderState()==4){
            BigDecimal i1 = new BigDecimal("0");
            orderss.setOrderState(4);
            orderss.setRemainingAmount(i1);
        }
        boolean updateById = orderssService.updateById(orderss);
        JsonMassage<Orderss> jsonMassage = new JsonMassage<Orderss>(200, "ok", null, null);
        return jsonMassage;


    }

    @RequestMapping("orderssAll")
    public JsonMassage<List<Orderss>> orderssList() {
        List<Orderss> list = orderssService.list();
        JsonMassage<List<Orderss>> jsonMas = new JsonMassage<List<Orderss>>(200, "ok", null, list);
        return jsonMas;
    }

    /**
     * 解析地址
     *
     * @param origin
     * @param addressA
     * @return
     */
    @RequestMapping("/Lng")
    public JsonMassage<Map<String, Double>> LngLat(
            @RequestParam(value = "origin[]", required = false) String[] origin,
            String addressA
    ) {

        JsonMassage<Map<String, Double>> jsonMap = null;
        if (origin != null) {

            String orderFromTitle = "";
            for (int i = 0; i < origin.length; i++) {
                QueryWrapper<DouArea> queryWrap = new QueryWrapper<DouArea>();
                queryWrap.eq("area_id", origin[i]);
                orderFromTitle += douAreaService.getOne(queryWrap).getName();
            }
            if (addressA != null) {
                orderFromTitle += addressA;
            }
            //正地理编码服务
            //获取始发地 X/Y值
            Map<String, Double> stringDoubleMap = locationUtils.AddressTolongitudea(orderFromTitle);
            /* String abcab = "{lng:" + String.valueOf(stringDoubleMap.get("lng")) + ",lat:" + String.valueOf(stringDoubleMap.get("lat")) + "}";*/
            jsonMap = new JsonMassage<Map<String, Double>>(200, "ok", null, stringDoubleMap);
        }
        return jsonMap;
    }

    /**
     * 解析地址
     *
     * @param destination
     * @param addressB
     * @return
     */
    @RequestMapping("/lat")
    public JsonMassage<Map<String, Double>> lat(
            @RequestParam(value = "destination[]", required = false) String[] destination,
            String addressB
    ) {
        JsonMassage<Map<String, Double>> jsonMap = null;
        if (destination != null) {
            //获取始发地地址
            String orderFromTitle = "";
            for (int i = 0; i < destination.length; i++) {
                QueryWrapper<DouArea> queryWrap = new QueryWrapper<DouArea>();
                queryWrap.eq("area_id", destination[i]);
                orderFromTitle += douAreaService.getOne(queryWrap).getName();
            }
            if (addressB != null) {
                orderFromTitle += addressB;
            }
            //正地理编码服务
            //获取始发地 X/Y值
            Map<String, Double> stringDoubleMap = locationUtils.AddressTolongitudea(orderFromTitle);
            //  String abcab = "{lng:" + String.valueOf(stringDoubleMap.get("lng")) + ",lat:" + String.valueOf(stringDoubleMap.get("lat")) + "}";
            jsonMap = new JsonMassage<Map<String, Double>>(200, "ok", null, stringDoubleMap);
        }
        return jsonMap;
    }

    @RequestMapping("/distance")
    public JsonMassage<String> distance(
            @RequestParam(value = "origin[]", required = false) String[] origin,
            @RequestParam(value = "destination[]", required = false) String[] destination,
            String addressA,
            String addressB
    ) {
        DecimalFormat aa = null;
        Double distance = null;
        if (origin != null && destination != null) {
            //获取始发地地址
            String orderFromTitle = "";
            for (int i = 0; i < origin.length; i++) {
                QueryWrapper<DouArea> queryWrap = new QueryWrapper<DouArea>();
                queryWrap.eq("area_id", origin[i]);
                orderFromTitle += douAreaService.getOne(queryWrap).getName();
            }
            if (addressA != null) {
                orderFromTitle += addressA;
            }
            //获取目的地地址
            String orderFinishTitle = "";
            for (int i = 0; i < destination.length; i++) {
                QueryWrapper<DouArea> queryWrap = new QueryWrapper<DouArea>();
                queryWrap.eq("area_id", destination[i]);
                orderFinishTitle += douAreaService.getOne(queryWrap).getName();
            }
            if (addressB != null) {
                orderFinishTitle += addressB;
            }
            //获取两地之间的距离
            //始发地地址坐标
            String start = locationUtils.getCoord(orderFromTitle);
            //目的地地址坐标
            String end = locationUtils.getCoord(orderFinishTitle);
            distance = (locationUtils.getDistance(start, end) / 1000);
            aa = new DecimalFormat("0.00");
        }
        JsonMassage<String> jsonMas = new JsonMassage<String>(200, "ok", null, aa.format(distance));
        return jsonMas;
    }

    @RequestMapping("/distributeUp")
    public JsonMassage<Orderss> distributeUp(Long ordersId, Long vehicleId) {

        Orderss orderss = new Orderss();
        orderss.setOrderId(ordersId);
        orderss.setVehicleId(vehicleId);
        orderss.setOrderState(2);
        boolean b = orderssService.updateById(orderss);

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(vehicleId);
        vehicle.setVehicleStatus(2);
        boolean b1 = vehicleService.updateById(vehicle);
        JsonMassage<Orderss> jsonMassage = new JsonMassage<Orderss>(200, "ok", null, null);
        return jsonMassage;
    }
    @RequestMapping("/urls")
    public JsonMassage<String[]>  urls (Integer orderId) {
        Orderss orderss = orderssService.getById(orderId);
        String[] split = orderss.getContractPicture().split(",");
        JsonMassage<String[]> jsonMas = new JsonMassage<String[]>(200, "ok", null, split);
        return jsonMas;
    }
}

