package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.pojo.DouArea;
import com.hz.pojo.Orderss;
import com.hz.service.DouAreaService;
import com.hz.service.GoodsService;
import com.hz.service.OrderssService;
import com.hz.utils.JsonMassage;
import com.hz.utils.LocationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.geom.Point2D;
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
            String addressB
    ) {


        //生成订单编号    18位   2022 0505 1520 55xx xx
        String waybillId = "";
        //获取时间戳+4位随机数
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        waybillId = df.format(new Date()) + (int) ((Math.random() * 9 + 1) * 1000);//2022 0505 1539 3361 17
        orderss.setWaybillId(Long.valueOf(waybillId));

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

            //获取两地之间的距离
            //始发地地址
            Point2D pointDD = new Point2D.Double(stringDoubleMap.get("lng"), stringDoubleMap.get("lat"));
            //目的地地址
            Point2D pointXD = new Point2D.Double(stringDoubleMap1.get("lng"), stringDoubleMap1.get("lat"));
            double distance = (locationUtils.getDistance(pointDD, pointXD) / 1000);
            DecimalFormat aa = new DecimalFormat("0.00");
            orderss.setOrderMileage(aa.format(distance));
        }


        boolean save = orderssService.save(orderss);


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

}

