Your name and student ID number
	Lazaro Sanchez Campos (A20362245)
	
Answers to the following questions:
	i. How do you run your program (i.e. what is the command line)?
		cd PointOfSale
		set classpath=C:\Program Files\Java\jdk1.7.0_79\bin;
		set path=C:\Program Files\Java\jdk1.7.0_79\bin;
		cd src
		javac ./PointofSale.java
		java -classpath . PointOfSale <costs file> <sales file> <register tape file>
	
	ii. Describe your object-oriented design for the program:
		A. How are you implementing the costs?
			I read the costs file using the method readInitialCostsFile and then I add the items into the itemList if they are not there, 
			or add the missing parameters of the existent items.
		B. How are you implementing the sales?
			I read the sales file using the method readInitialSalesFile and then I add the items into the itemList.
			Each type of sale has its own class. Item is a superclass (item with no sale), and I have 3 subclasses 
			(BuyXForTheCostOfY, BuyXGetOneFree, Discount), which extend the Item class and override its constructor 
			and getUnitCost method adding different parameters depending on the type of sale. They also override toString method in order to
			print well each sale.
		C. How are you implementing a customer’s order?
			I have created a class that has all the items of the customer's order in an ArrayList of Item, it also has the total cost and total items of the order.
	
	iii. What specific problems or challenges did you have implementing your solution? For example,
	was there a particular requirement that you had difficulty implementing? Or perhaps
	there was a particularly nasty bug in your implementation you had problems tracking down.
	I had some problems with the specific format of numbers, but finally I could fix these problems.
	
	iv. Were there any requirements that were not implemented or not implemented properly in
	your solution? If so, please elaborate.
	No.
	
	v. Were there any requirements that were vague or open to interpretation that you had to make
	a decision on how to implement? How did you elect to interpret them?
	No.
	
	vi. How would you rate the complexity of this MP on a scale of 1 to 10 where 1 is very easy
	and 10 is very difficult. Why did you give this rating?
	I think that this MP was at the same level than other ones, and it is also very instructive. Then, I would rate the complexity of this MP with 7.