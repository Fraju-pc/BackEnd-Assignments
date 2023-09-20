package Week04;

import java.util.ArrayList;
import java.util.List;

public class IceCreamOrder {
String user;
String size;
String cupOrCone;
List<String> flavors = new ArrayList<String>();

// Default Constructor
public IceCreamOrder() { 
this.user = "";
this.flavors.add(null);
this.size = "";
this.cupOrCone = "";
}
// Additional Constructors
public IceCreamOrder(String user, List<String> flavors) {
this.user = user;
this.flavors = flavors;
}
public IceCreamOrder(String user, List<String> flavors, String size, String cupOrCone) {
this.user = user;
this.flavors = flavors;
this.size = size;
this.cupOrCone = cupOrCone;
}
//Getters & Setters
public String getUser() { return this.user; }
public void setUser(String user) { this.user = user; }
public List<String> getFlavors() { return this.flavors; }
public void setFlavors(List<String> flavors) { this.flavors = flavors; }
public String getSize() { return this.size; } 
public void setSize(String size) { this.size = size; } 
public String getCupOrCone() { return this.cupOrCone; } 
public void setCupOrCone(String cupOrCone) { this.cupOrCone = cupOrCone; }

// Describe Method
public void describe() {
int num = 0;
int boxSize = 40;
char star = '*';

for (int i = 0; i < boxSize; i++) {
System.out.print(star);
}
System.out.println("\nIce Cream Order:\n----------------");
System.out.print("Name:\n\t" + this.user + "\nOrder: \n\t");
System.out.println(this.size + " " + this.cupOrCone);

if (this.flavors.size() > 1) {
System.out.print("Flavors:\n\t");
} else {
System.out.print("Flavor:\n\t");
}
if (this.flavors.isEmpty()) {
System.out.println("No ice cream flavors were chosen!");
} else {
for (String flavor : this.flavors) {
System.out.print(flavor);
num++;
if (num == flavors.size()-1) {
System.out.print (" & \n\t");
} else if (num < flavors.size()-1) {
System.out.print (", \n\t");
}
}
}
System.out.println();
for (int i = 0; i < boxSize; i++) {
System.out.print(star);
}
}
} // end of IceCreamOrder