public class TradeImpl implements Trade
{
	private final Type type;
	private final Double quantity;
	private final Double price;
	private final Boolean success;
	
	public TradeImpl(Type type, Double quantity, Double price, Boolean success)
	{
		this.type = type;
		this.quantity = quantity;
		this.price = price;
		this.success = success;
	}
	
	public Type getType()
	{
		return type;
	}
	
	public Double getQuantity()
	{
		return quantity;
	}
	
	public Double getPrice()
	{
		return price;
	}

	public Boolean isSuccess()
	{
		return success;
	}
	
	public Double total()
	{
		return quantity * price;
	}
}