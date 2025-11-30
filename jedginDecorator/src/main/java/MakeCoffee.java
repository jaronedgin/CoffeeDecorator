
public class MakeCoffee {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coffee order =new Sugar(new Cream( new ExtraShot(new BasicCoffee())));
		
		//Coffee order2 = new ExtraShot(order);
		
		double costOne = order.makeCoffee();
		System.out.println("Total: $"+ String.format("%.2f", costOne));
		/*double cost = order2.makeCoffee();
		System.out.println("Total: $"+ String.format("%.2f", cost));
		*/	
	}
}

interface Coffee{
		double makeCoffee();
}

class BasicCoffee implements Coffee{

	private double cost = 4.50;
	
	@Override
	public double makeCoffee() {
		// TODO Auto-generated method stub
		System.out.println("Black Coffee: $4.50");
		
		return cost;
	}	
}

abstract class CoffeeDecorator implements Coffee{
	protected Coffee specialCoffee;
	
	public CoffeeDecorator(Coffee specialCoffee) {
		this.specialCoffee = specialCoffee;
	}
	
	public double makeCoffee() {
		return specialCoffee.makeCoffee();
	}
}

class ExtraShot extends CoffeeDecorator{

	private double cost = 1.20;

	ExtraShot(Coffee specialCoffee){
		super(specialCoffee);
	}
	
	public double makeCoffee() {
		return specialCoffee.makeCoffee() + addShot();
	}
	
	private double addShot() {
		System.out.println(" + extra shot: $1.20");

		return cost;
	}
}

class Cream extends CoffeeDecorator{

	private double cost = .50;
	Cream(Coffee specialCoffee){
		super(specialCoffee);
	}
	
	public double makeCoffee() {
		return specialCoffee.makeCoffee() + addCream();
	}
	
	private double addCream() {
		
		System.out.println(" + cream: $.50");

		return cost;
	}
}

class Sugar extends CoffeeDecorator{

	private double cost = .50;

	Sugar(Coffee specialCoffee){
		super(specialCoffee);
	}
	
	public double makeCoffee() {
		return specialCoffee.makeCoffee()+ addSugar();
	}
	
	private double addSugar() {
		
		System.out.println(" + sugar: $.50");

		return cost;
	}
}

class Syrup extends CoffeeDecorator{

    private double cost = .75;

    Syrup(Coffee specialCoffee){super(specialCoffee);}

    public double makeCoffee() {return specialCoffee.makeCoffee() + addSyrup();}

    private double addSyrup() {

        System.out.println(" + syrup: $.75");

        return cost;
    }
}

class Blend extends CoffeeDecorator{

    private double cost = 1.00;

    Blend(Coffee specialCoffee){super(specialCoffee);}

    public double makeCoffee() {return specialCoffee.makeCoffee() + blend();}

    private double blend() {

        System.out.println(" + blended: $1.00");

        return cost;
    }
}

class CoffeeOrder {
    private Coffee order;

    public CoffeeOrder() {
        order = new BasicCoffee();
    }

    public void addExtraShot() {
        order = new ExtraShot(order);
    }

    public void addCream() {
        order = new Cream(order);
    }

    public void addSugar() {
        order = new Sugar(order);
    }

    public void addSyrup() {
        order = new Syrup(order);
    }

    public void blend() {
        order = new Blend(order);
    }

    public void remove(Class<? extends CoffeeDecorator> type) {
        order = removeHelper(order, type);
    }

    public Coffee removeHelper(Coffee coffee, Class<? extends CoffeeDecorator> type) {
        if (!(coffee instanceof CoffeeDecorator)) {
            return coffee;
        }

        CoffeeDecorator root =  (CoffeeDecorator) coffee;

        if (coffee.getClass() == type) {
            return root.specialCoffee;
        }

        root.specialCoffee = removeHelper(root.specialCoffee, type);

        return root;
    }

    public double make() {
        return order.makeCoffee();
    }

    public void resetOrder() {
        order = new BasicCoffee();
    }
}





