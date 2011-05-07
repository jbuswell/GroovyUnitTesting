import java.util.*;

public class StockTraderServiceMock implements StockTraderService
{
	private Boolean exists;
	public Double price;
	private Trade trade;
	private List<String> mostActive;
	
	public Boolean exists(String symbol)
	{
		return exists;
	}
	
	public Double getPrice(String symbol)
	{
		return price;
	}

	public Trade purchaseAtCurrentPrice(String symbol)
	{
		return trade;
	}
	
	public Trade sell(String symbol, Double price)
	{
		return trade;
	}
	
	public List<String> getMostActive(Integer num)
	{
		return mostActive;
	}
	
	public void setExists(Boolean exists) 
	{
		this.exists = exists;
	}
	
	public Boolean getExists()
	{
		return this.exists;
	}
	
	public void setPrice(Double price) 
	{
		this.price = price;
	}
	
	public Double getPrice()
	{
		return this.price;
	}

	public void setTrade(Trade trade) 
	{
		this.trade = trade;
	}

	public Trade getTrade()
	{
		return this.trade;
	}

	public void setMostActive(List<String> mostActive) 
	{
		this.mostActive = mostActive;
	}

	public List<String> getMostActive()
	{
		return this.mostActive;
	}
}