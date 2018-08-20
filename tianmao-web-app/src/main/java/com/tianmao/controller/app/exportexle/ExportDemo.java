/*
package com.tianmao.controller.app.export;

import cn.wlxsh.common.util.lang.BigDecimalUtil;
import cn.wlxsh.common.util.lang.DateUtil;
import cn.wlxsh.common.util.lang.StringUtil;
import com.alipay.api.domain.OrderDetail;
import com.tianmao.app.util.ExportUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExportDemo {
    */
/**
     * 门店销售情况
     *
     * @param iStoreReport
     * @param response
     *//*

    @RequestMapping("/shopLineData")
    @ResponseBody
    public void categoryLineData(IStoreReport iStoreReport, HttpServletResponse response) {
        String[] excelHead = new String[]{"序号", "商品名称", "条形码", "支付金额", "支付时间", "支付类型"};
        String titleName = "门店销售情况报表";
//        List<IStoreReport> iStoreReports = iStoreReportClient.shopReport(iStoreReport);
//        IstoreReportExcel(iStoreReport, response, excelHead, titleName, iStoreReports, order);
        Order order = new Order();
        order.setBeginTime(iStoreReport.getBeginTime());
        order.setEndTime(iStoreReport.getEndTime());
        order.setShopId(iStoreReport.getShopId());
        order.setChannelId(iStoreReport.getChannelId());
        Order collect = orderClient.getCollect(order);
//        订单总数量
        List<OrderDetail> orderDetailList = orderdetailClient.getOrdertotal(order);
        IstoreReportExcel(iStoreReport, response, excelHead, titleName, orderDetailList, collect);
    }


    public void IstoreReportExcel(IStoreReport iStoreReport, HttpServletResponse response, String[] excelHead, String titleName, List<OrderDetail> orderDetailList, Order order) {
        List<String[]> dataList = new ArrayList<>();
        String date = " ";
        String shopName = "爱士多无人便利店";
        if (iStoreReport.getShopId() != null) {
            Shop shop = shopClient.getShopById(iStoreReport.getShopId());
            if (shop != null) {
                shopName = shopName + "（" + shop.getShopName() + "）";
            }
        }
        Date beginTime = iStoreReport.getBeginTime();
        Date endTime = iStoreReport.getEndTime();
        if (beginTime != null && endTime != null) {
            date = DateUtil.formatDate(beginTime, DateUtil.DATE_DEFAULT_STR) + " - " + DateUtil.formatDate(endTime, DateUtil.DATE_DEFAULT_STR);
            date = shopName + date + "交易明细报表";
        } else if (beginTime != null) {
            date = DateUtil.formatDate(beginTime, DateUtil.DATE_DEFAULT_STR) + " 之后";
            date = shopName + date + "交易明细报表";
        } else if (endTime != null) {
            date = DateUtil.formatDate(endTime, DateUtil.DATE_DEFAULT_STR) + " 之前";
            date = shopName + date + "交易明细报表";
        } else {
            date = shopName + date + "交易明细报表";
        }

        dataList.add(excelHead);
        Double sum = 0.00;
        for (int i = 0; i < orderDetailList.size(); i++) {
            OrderDetail orderDetail = orderDetailList.get(i);
            String[] data = new String[10];
            data[0] = StringUtil.objToString(i + 1);
//            data[1] = StringUtil.objToString(istoreReport.getHead());
//            data[2] = StringUtil.objToString(istoreReport.getTotalOrder());
//            data[3] = StringUtil.objToString(istoreReport.getTotalMoney());
            data[1] = StringUtil.objToString(orderDetail.getGood().getGoodName());
            data[2] = StringUtil.objToString(orderDetail.getGood().getGoodCode());
            data[3] = StringUtil.objToString(orderDetail.getPrice());
            data[4] = StringUtil.objToString(DateUtil.formatDate(orderDetail.getPayTime()));
            data[5] = StringUtil.objToString(orderDetail.getPayType().getRemark());
            sum += BigDecimalUtil.sub(orderDetail.getPrice(),orderDetail.getGood().getStockPrice()).doubleValue();
            dataList.add(data);
        }
//        order.getOrdersMoney();//订单总金额
//        order.getWeChatMoney();//微信支付金额
//        order.getAlipayMoney();//支付宝支付金额
//        orderDetailList.size();//销售总数量
//        order.getOrdersTotal();//收款笔数
        Object ob[] = new Object[]{order.getOrdersMoney(),order.getWeChatMoney(),order.getAlipayMoney(),orderDetailList.size(),order.getOrdersTotal(),sum};
        String array[] = new String[]{"订单总金额", "微信支付金额", "支付宝支付金额", "销售总数量", "收款笔数", "利润"};
        for (int i = 0; i < array.length; i++) {
            String[] data = new String[10];
            data[1] = array[i];
            data[2] = StringUtil.objToString(ob[i]);
            dataList.add(data);
        }
       */
/* String[] data = new String[10];
        data[0] = StringUtil.objToString(iStoreReportsList.size() + 1);
        data[1] = "订单总金额";
        data[2] = StringUtil.objToString(sum);*//*

        OutputStream os = null;
        try {
            String title = titleName;
            String filename = new String((title + ".xls").getBytes("gbk"), "iso8859-1");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            ExportUtil.createExcelFile2(response.getOutputStream(), title, dataList, title, date);
        } catch (
                Exception e)

        {
            logger.error(titleName + "导出失败", e);
        } finally

        {
            if (null != os) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }
}
*/
