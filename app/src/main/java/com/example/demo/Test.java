package com.example.demo;

/**
 * @author: sunlulu.tomato
 * @date: 2020-12-20
 */
public class Test {

    public class OrderInfo {
        public String productNo;
    }
    public class ProductInfo {
        public String producerNo;
    }

    public void createOrder(String productNo) {
        // 创建获取订单信息的任务
        GetOrderInfoTask task = new GetOrderInfoTask(productNo);
        // 设置创建订单的回调
        task.setCallback(new CreateOrderBack() {
            @Override
            public void createOrder(OrderInfo orderInfo) {
                // 创建插入订单的任务
                InsertOrderTask insertOrderTask = new InsertOrderTask(orderInfo);
                // 设置减库存的回调
                insertOrderTask.setReduceStoreBack(new ReduceStoreBack() {
                    @Override
                    public void reduceStore(String productNo) {
                        reduceStore(orderInfo.productNo);
                    }
                });
                threadPool.submit(insertOrderTask);
            }
        });
        // 执行获取订单信息的任务
        threadPool.submit(task);
    }

    /**
     * 获取订单信息任务
     */
    public static class GetOrderInfoTask implements Runnable {

        private String productNo;
        private CreateOrderBack callback;

        public GetOrderInfoTask(String productNo) {
            this.productNo = productNo;
        }

        @Override
        public void run() {
            if (productNo == null || "".equals(productNo)) {
                return;
            }
            // 获取商品信息
            ProductInfo productInfo = getItemInfo(productNo);
            if (productInfo == null) {
                return;
            }
            // 整理订单信息
            OrderInfo orderInfo = convert2OrderInfo(productInfo);
            // 在这里执行创建订单回调任务
            callback.createOrder(orderInfo);
        }

        public void setCallback(CreateOrderBack callback) {
            this.callback = callback;
        }
    }

    /**
     * 插入订单任务
     */
    public static class InsertOrderTask implements Runnable {
        private OrderInfo orderInfo;
        private ReduceStoreBack reduceStoreBack;

        public InsertOrderTask(OrderInfo orderInfo) {
            this.orderInfo = orderInfo;
        }

        @Override
        public void run() {

            // do something

            // 在这里执行减库存回调
            reduceStoreBack.reduceStore(orderInfo.productNo);
        }

        public void setReduceStoreBack(ReduceStoreBack callback) {
            this.reduceStoreBack = callback;
        }
    }

    public interface CreateOrderBack {
        void createOrder(OrderInfo orderInfo);
    }

    public interface ReduceStoreBack {
        void reduceStore(String productNo);
    }
}


