public interface Trade 
{
	public enum Type { buy, sell }
	
	Type getType();
	Double getQuantity();
	Double getPrice();
	Boolean isSuccess();
	Double total();
}