import org.junit.*
import groovy.util.GroovyTestCase

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
		stockTrader.stockTraderService=service
		assertArrayEquals(stocks.toArray(), stockTrader.tenMostActive().toArray())
	}

	@Test 
	void test_get_most_active_case() 
	{
		def stocks = ['APPL', 'GOOG', 'MSFT', 'INTC', 'IBM', 'ORCL', 'ADBE', 'RIMM', 'HPQ', 'csco']
		def service = [ 'getMostActive' : { return stocks } ] as StockTraderService
		stockTrader.stockTraderService=service
		def result = stockTrader.tenMostActive()
		assert stocks.collect{ it.toUpperCase() } == result
	}
}
