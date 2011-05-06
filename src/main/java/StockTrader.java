import java.util.*;

public class StockTrader
{
	private StockTraderService stockTraderService;
	
	public boolean buy(String symbol)
	{
		Trade trade = stockTraderService.purchaseAtCurrentPrice(symbol);
		return trade.isSuccess();
	}
	
	public List<String> tenMostActive()
	{
		List<String> mostActive = stockTraderService.getMostActive(10);
		List<String> upperCase = new ArrayList<String>();
		for(String x : mostActive)
		{
			upperCase.add(x.toUpperCase());
		}
		return upperCase;
	}
	
	public StockTraderService getStockTraderService()
	{
		return stockTraderService;
	}
	
	public void setStockTraderService(StockTraderService stockTraderService)
	{
		this.stockTraderService = stockTraderService;
	}
}
