package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.AllMarketTickersEvent;
import com.binance.api.client.domain.market.CandlestickInterval;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Market data stream endpoints examples.
 * <p>
 * It illustrates how to create a stream to obtain updates on market data such as executed trades.
 */
public class MarketDataStreamExample {

    public static void main(String[] args) throws InterruptedException, IOException {
//      allTicker();
      BinanceApiWebSocketClient client = BinanceApiClientFactory.newInstance().newWebSocketClient();
        client.onDepthEvent("ethbtc@depth20", System.out::println);

    }

  private static void allTicker() throws InterruptedException {
    WSData binance = new WSData();
    BinanceApiWebSocketClient client = BinanceApiClientFactory.newInstance().newWebSocketClient();
    client.onAllMarketTickersEvent(binance::updateData);

    while (true){
      Thread.sleep(1000);
      System.out.println(binance.getData().size());
    }
  }

  static class WSData {

        private Map<String, AllMarketTickersEvent> data = new HashMap<>();

        public void updateData(List<AllMarketTickersEvent> list) {
            list.forEach(e -> data.put(e.getSymbol(), e));
        }

        public Map<String, AllMarketTickersEvent> getData(){
          return data;
        }


    }
}
