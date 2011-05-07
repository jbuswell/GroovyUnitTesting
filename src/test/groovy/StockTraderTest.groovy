import org.junit.*
import groovy.util.GroovyTestCase

import static Trade.*

class StockTraderTest extends GroovyTestCase
{
	StockTrader stockTrader = new StockTrader();
	
	@Test
	void test_buystock_valid()
	{
		def trade = [ 'isSuccess' : { true } ] as Trade
		def service = [ 'purchaseAtCurrentPrice' : { trade } ] as StockTraderService
		stockTrader.setStockTraderService(service)
		assertTrue(stockTrader.buy("AAPL"))
	}
	
	@Test 
	void test_get_most_active() 
	{
		def stocks = ['APPL', 'GOOG', 'MSFT', 'INTC', 'IBM', 'ORCL', 'ADBE', 'RIMM', 'HPQ', 'CSCO']
		def service = [ 'getMostActive' : { return stocks } ] as StockTraderService
		stockTrader.stockTraderService = service
		assertArrayEquals(stocks.toArray(), stockTrader.tenMostActive().toArray())
	}

	@Test 
	void test_get_most_active_case() 
	{
		def stocks = ['APPL', 'GOOG', 'MSFT', 'INTC', 'IBM', 'ORCL', 'ADBE', 'RIMM', 'HPQ', 'csco']
		def service = [ 'getMostActive' : { return stocks } ] as StockTraderService
		stockTrader.stockTraderService = service
		def result = stockTrader.tenMostActive()
		assert stocks.collect{ it.toUpperCase() } == result
	}
	
	@Test
	void test_sell_at_market()
	{
		def trade = new TradeImpl(Type.sell, 100d, 100.50d, true)
		def expando = new Expando()
		expando.getPrice = { return 100.50d }
		expando.sell = { s, q -> return trade }
		expando.exists = { return true }
		stockTrader.stockTraderService = expando as StockTraderService
		def result = stockTrader.sellAtMarket("MSFT", 100);
		assert (100.50 * 100) == result
	}
}
