package com.company;

import java.util.List;

/**
 * @author Jaedoo Lee
 */
public class Order {

    private List<OrderLine> orderLines;
    private int totalAmounts;
    private OrderState state;
    private ShippingInfo shippingInfo;

    public Order(Orderer orderer, List<OrderLine> orderLines, ShippingInfo shippingInfo, OrderState state) {
        setOrderer(orderer);
        setOrderLines(orderLines);
        // ... 다른 값 설정
    }

    private void setOrderer(Orderer orderer) {
        if (orderer == null)    throw new IllegalArgumentException("no orderer");
        this.orderer = orderer;
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        if (shippingInfo == null) {         //  배송지 정보 필수 규칙 구현
           throw new IllegalArgumentException("no ShippingInfo");
        }
        this.shippingInfo = shippingInfo;
    }

    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("no OrderLine");
        }
    }

    private void calculateTotalAmounts() {
        this.totalAmounts = new Money(orderLines.stream().mapToInt(x -> x.getAmounts().getValue()).sum());
    }

    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        verifyNotYetShipped();
        setShippingInfo(newShippingInfo);
        /*if (!state.isShippingChangeable()) {
            throw new IllegalStateException("can't change shipping in " + state);
        }*/
    }

    public void cacnel() {
        verifyNotYetShipped();
        this.state = OrderState.CANCELED;
    }

    private void verifyNotYetShipped() {
        if (state != OrderState.PAYMENT_WAITING && state != OrderState.PREPARING) {
            throw new IllegalStateException("already ahipped");
        }
    }

    public void changeShipped() {
        // 로직 검사
        this.state = OrderState.SHIPPED;
    }
    //...
}
