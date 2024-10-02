import React, { useEffect, useState } from 'react';
import styles from './OrdersPage.module.css';


// Mock function to simulate fetching orders data
const fetchOrdersData = () => {
    return new Promise((resolve) =>
        setTimeout(() => {
            resolve([
                { id: 1, date: '2024-08-01', items: ['Item 1', 'Item 2'], total: '$50.00' },
                { id: 2, date: '2024-08-15', items: ['Item 3', 'Item 4'], total: '$75.00' },
                { id: 3, date: '2024-08-22', items: ['Item 5'], total: '$25.00' }
            ]);
        }, 1000)
    );
};

const OrdersPage = () => {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        fetchOrdersData().then(data => {
            setOrders(data);
        });
    }, []);

    return (
        <div className={styles.container}>
            <h1>Your Orders</h1>
            {orders.length === 0 ? (
                <p>No orders found.</p>
            ) : (
                <ul className={styles.ordersList}>
                    {orders.map(order => (
                        <li key={order.id} className={styles.orderItem}>
                            <h2>Order #{order.id}</h2>
                            <p>Date: {order.date}</p>
                            <p>Items: {order.items.join(', ')}</p>
                            <p>Total: {order.total}</p>
                            
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default OrdersPage;
