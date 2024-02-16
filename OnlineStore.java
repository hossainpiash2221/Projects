/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package online;
import java.util.Scanner;
import java.util.*;

class Node {
    int data;
    String name;
    String Pname;
    String place;
    String mobile;
    Node next;

    Node(int data, String name, String Pname, String place,String mobile) {
        this.data = data;
        this.name = name;
        this.Pname=Pname;
        this.mobile = mobile;
        this.place = place;
        this.next = null;
    }
}

class ProductNode {
    String productName;
    int quantity;
    float price;
    int data;

    ProductNode next;

    ProductNode(int data, String productName, float price) {
        this.data = data;
        this.price = price;
        this.quantity = 0;
        this.productName = productName;
        this.next = null;
    }
}

 class OnlineStore {
    static ProductNode headAdmin = null, tailAdmin = null;
    static ProductNode headCustomer = null, tailCustomer = null;
    static Node headQueue = null, rear = null, front = null;

    public static void main(String[] args) {
        // Display some list initially
        createAdminProduct(1, "Iphone", 450);
        createAdminProduct(2, "Xioami", 250);
        createAdminProduct(3, "Oppo", 350);

        while (true) {
            displayMainMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    customerLogin();
                    break;
                case 2:
                    managerLogin();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input! Please enter a valid option.");
            }
        }
    }

     static void displayMainMenu() {
        System.out.println("\n\n\t\t\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\t\t\tWelcome to Sanon Online Store");
        System.out.println("\t\t\t\t\t\t-----------------------------");
        System.out.println("\n\n\t\t\t\t1. Login As Customer \t2. Login As Manager\t3. Exit");
        System.out.print("Type here: ");
    }

     static void customerLogin() {
        Scanner scanner = new Scanner(System.in);
        String username, password;

        System.out.print("\nUsername: ");
        username = scanner.next();
        System.out.print("Password: ");
        password = scanner.next();

        if (username.equals("piash") && password.equals("123")) {
            System.out.println("\n\n\t\t\t\t\t\t   ---------------------");
            System.out.println("\t\t\t\t\t\t    Welcome Back, " + username);
            System.out.println("\t\t\t\t\t\t   ---------------------\n");
            operateCustomer();
        } else {
            System.out.println("\n\t\t\t\t\t\tIncorrect username or password");
        }
    }

     static void operateCustomer() {
        int flag = 0;

        while (true) {
            displayCustomerMenu();
            Scanner scanner = new Scanner(System.in);
            int opt = scanner.nextInt();

            switch (opt) {
                case 1:
                    displayOrderMenu();
                    int orderOption = scanner.nextInt();
                    switch (orderOption) {
                        case 1:
                            displayAdminProductList();
                            System.out.print("\nEnter number to order: ");
                            int productId = scanner.nextInt();
                            System.out.print("Enter quantity: ");
                            int quantity = scanner.nextInt();
                            createCustomerProduct(productId, quantity);
                            break;
                        case 2:
                            displaySortedAdminProductList();
                            System.out.print("\nEnter number to order: ");
                            int sortedProductId = scanner.nextInt();
                            System.out.print("Enter quantity: ");
                            int sortedQuantity = scanner.nextInt();
                            createCustomerProduct(sortedProductId, sortedQuantity);
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    break;
                case 2:
                    displayCustomerProductList();
                    break;
                case 3:
                    deleteCustomerProduct();
                    displayCustomerProductList();
                    break;
                case 4:
                    displayBill();
                    deleteCustomerList();
                    System.out.print("Go back? Press 'Y' to go back or any other key to exit: ");
                    char ch = scanner.next().charAt(0);
                    if (ch == 'Y' || ch == 'y') {
                        flag = 1;
                    }
                    break;
                case 5:
                    searchAdminProduct();
                    break;
                case 6:
                    enqueue();
                    break;
                case 7:
                    flag = 1;
                    break;
                default:
                    System.out.println("Wrong input");
            }

            if (flag == 1) {
                break;
            }
        }
    }

     static void displayCustomerMenu() {
        System.out.println("\n\t\t\t\t\t\t    Customer MENU");
        System.out.println("\t\t\t\t\t\t   ---------------");
        System.out.println("1. Place Order\n2. Check Cart\n3. Delete a product from cart\n4. Confirm Bill\n5. Search a product\n6. Product Request\n7. Back");
        System.out.print("\nType here: ");
    }

     static void displayOrderMenu() {
        System.out.println("\n1. See the menu default\n2. See the menu in ascending order");
        System.out.print("Type here: ");
    }

     static void displayAdminProductList() {
        System.out.println("\n\n\t\t\t\t\t\t  Order Menu");
        System.out.println("\t\t\t\t\t\t  ---------------");
        System.out.println("\n\t\t\t\t\tID\t Name\t\tPrice");
        displayProductList(headAdmin);
    }

     static void displaySortedAdminProductList() {
        System.out.println("\n\n\t\t\t\t\t\t  Order Menu");
        System.out.println("\t\t\t\t\t\t  ---------------");
        System.out.println("\n\t\t\t\t\tID\t Name\t\tPrice");
        headAdmin = mergesort(headAdmin);
        
         //  ProductNode temp2 = headAdmin;
    
       // ProductNode temp= mergesort(temp2);
        
        displayProductList(headAdmin);
    }

     static void displayCustomerProductList() {
        System.out.println("\n\n\t\t\t\t\t\t  Order list");
        System.out.println("\t\t\t\t\t\t  ---------------");
        System.out.println("\n\t\t\t\t\tID\t Name\tQuantity\tPrice");
        displayProductList(headCustomer);
    }

     static void displayProductList(ProductNode head) {
        ProductNode temp = head;
        while (temp != null) {
            if (temp.quantity == 0) {
                System.out.println("\t\t\t\t\t" + temp.data + "\t" + temp.productName + "\t\t" + temp.price);
            } else {
                System.out.println("\t\t\t\t\t" + temp.data + "\t" + temp.productName + "\t  " + temp.quantity + "\t\t" + temp.price);
            }
            temp = temp.next;
        }
              
        System.out.println();
    }

     static void displayBill() {
        displayProductList(headCustomer);
        ProductNode temp = headCustomer;
        float totalPrice = 0;

        while (temp != null) {
            totalPrice += temp.price;
            temp = temp.next;
        }

        System.out.println("\n\t\t\t\t\tTotal price:"+totalPrice);
    }

     static void deleteCustomerProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the serial number to delete: ");
        int num = scanner.nextInt();

        ProductNode temp = headCustomer;
        ProductNode prev = null;

        while (temp != null) {
            if (temp.data == num) {
                if (prev == null) {
                    headCustomer = temp.next;
                } else {
                    prev.next = temp.next;
                }
                break;
            }
            prev = temp;
            temp = temp.next;
        }
    }

     static void deleteCustomerList() {
        headCustomer = null;
    }

     static void searchAdminProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\nType the name of your product: ");
        String productName = scanner.next();

        ProductNode temp = headAdmin;
        boolean found = false;

        while (temp != null) {
            if (productName.equalsIgnoreCase(temp.productName)) {
                found = true;
                System.out.println("\n\t\t\t\t\t\t\t------------------------");
                System.out.println("\t\t\t\t\t\t\tThis product is available");
                System.out.println("\t\t\t\t\t\t\t------------------------\n");
                break;
            }
            temp = temp.next;
        }

        if (!found) {
            System.out.println("\n\t\t\t\t\t\t\t----------------------------");
            System.out.println("\t\t\t\t\t\t\tThis product is not available");
            System.out.println("\t\t\t\t\t\t\t----------------------------\n");
        }
    }

     static void createAdminProduct(int data, String productName, float price) {
        ProductNode newNode = new ProductNode(data, productName, price);

        if (headAdmin == null) {
            headAdmin = tailAdmin = newNode;
        } else {
            tailAdmin.next = newNode;
            tailAdmin = newNode;
        }
    }

     static void createCustomerProduct(int data, int quantity) {
        ProductNode temp1 = headAdmin;
        boolean flag = false;

        while (temp1 != null) {
            if (temp1.data == data) {
                flag = true;
                break;
            }
            temp1 = temp1.next;
        }

        if (flag) {
            ProductNode newNode = new ProductNode(data, temp1.productName, quantity * temp1.price);
            newNode.quantity = quantity;

            if (headCustomer == null) {
                headCustomer = tailCustomer = newNode;
            } else {
                tailCustomer.next = newNode;
                tailCustomer = newNode;
            }
        } else {
            System.out.println("\n\t\t\t\t\tWrong Input\n");
        }
    }

     static void enqueue() {
        Scanner scanner = new Scanner(System.in);
        int val;
        String name,product,address,mobile;

        Node newNode;

        System.out.print("\n\n\t\t\t\t\t\t   Enter An ID(1/2/3): ");
        val = scanner.nextInt();

        System.out.print("\t\t\t\t\t\t   Enter Name: ");
        name = scanner.next();
        
        System.out.print("\t\t\t\t\t\t   Enter Product: ");
        product = scanner.next();
        
        System.out.print("\t\t\t\t\t\t   Enter Adress: ");
        address = scanner.next();
        
         System.out.print("\t\t\t\t\t\t   Enter Mobile Number: ");
        mobile = scanner.next();
        
        

        newNode = new Node(val, name,product,address,mobile);
        newNode.next = null;

        if (front == null && rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

     static void dequeue() {
         
        if (front == null) {
            System.out.println("\nQUEUE EMPTY");
        } else {
           
            Node temp = front;
           
            System.out.printf("|%-8s|%-20s|%-15s|%-25s|%-15s|\n", "ID","NAME", "MOBILE NUMBER", "ADDRESS","Number");
              System.out.printf("|%-8d|%-20s|%-15s|%-25s|%-15s|\n",temp.data,temp.name,temp.Pname,temp.place,temp.mobile);
            
            Shortpath(temp.place);
            front = front.next;
            if (front == null) {
                rear = null;
            }
        }
    }

     static void printQueue() {
        Node temp = front;
         
         System.out.printf("|%-8s|%-20s|%-15s|%-25s|%-15s|\n", "ID","NAME", "MOBILE NUMBER", "ADDRESS","Number");
        if (front == null) {
            System.out.println("\n\t\t\tList is EMPTY");
        } else {
            while (temp.next != null) {
                System.out.println("\n\n\t\t\t\t\t\t\tProduct Details");
             //   System.out.println("\t\t\t\t\t\t\t---------------");
            //    System.out.println("\t\t\t\t\t\t\tID\t Name");
              //  System.out.println("\t\t\t\t\t\t\t" + temp.data + "\t" + temp.name);
              System.out.printf("|%-8d|%-20s|%-15s|%-25s|%-15s|\n",temp.data,temp.name,temp.Pname,temp.place,temp.mobile);
                System.out.println("");  
              Shortpath(temp.place);
              temp = temp.next;
            }
           // System.out.println("\t\t\t\t\t\t\tID\t Name");
          //  System.out.println("\t\t\t\t\t\t\t" + temp.data + "\t" + temp.name);
           System.out.printf("|%-8d|%-20s|%-15s|%-25s|%-15s|\n",temp.data,temp.name,temp.Pname,temp.place,temp.mobile);
        }
    }

     static ProductNode sortedNode(ProductNode head) {
        ProductNode end, r, p, q, temp;

        for (end = null; end != head.next; end = p) {
            for (r = p = head; p.next != end; r = p, p = p.next) {
                q = p.next;
                if (p.price > q.price) {
                    p.next = q.next;
                    q.next = p;
                    if (p != head) {
                        r.next = q;
                    } else {
                        head = q;
                    }
                    temp = p;
                    p = q;
                    q = temp;
                }
            }
        }
        return head;
    }

     static void managerLogin() {
        Scanner scanner = new Scanner(System.in);
        String username, password;

        System.out.print("\nUsername: ");
        username = scanner.next();

        System.out.print("Password: ");
        password = scanner.next();

        if (username.equals("piash") && password.equals("123")) {
            System.out.println("\n\n\t\t\t\t\t\t   -----------------------------");
            System.out.println("\t\t\t\t\t\t    Welcome Back, Manager(" + username + ")");
            System.out.println("\t\t\t\t\t\t   -----------------------------");
            operateAdmin();
        } 
     else {
        System.out.println("\n\t\t\t\t\t\tIncorrect username or password");
    }
}
  static void operateAdmin() {
    int flag = 0;

    while (true) {
        displayAdminMenu();
        Scanner scanner = new Scanner(System.in);
        int opt = scanner.nextInt();

        switch (opt) {
            case 1:
                displayAdminProductList();
                break;
            case 2:
                addAdminProduct();
                break;
            case 3:
                updateAdminProduct();
                break;
            case 4:
                deleteAdminProduct();
                break;
            case 5:
                printQueue();
                break;
            case 6:
                dequeue();
                break;
            case 7:
                flag = 1;
                break;
            default:
                System.out.println("Wrong input");
        }

        if (flag == 1) {
            break;
        }
    }
}

 static void displayAdminMenu() {
    System.out.println("\n\t\t\t\t\t\t    Manager MENU");
    System.out.println("\t\t\t\t\t\t   ---------------");
    System.out.println("1. Display Product List\n2. Add Product\n3. Update Product\n4. Delete Product\n5. View Product Requests\n6. Process Product Request\n7. Back");
    System.out.print("\nType here: ");
}

 static void addAdminProduct() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("\nEnter product name: ");
    String productName = scanner.next();
    System.out.print("Enter product price: ");
    float price = scanner.nextFloat();

    int data;
    if (headAdmin == null) {
        data = 1;
    } else {
        data = tailAdmin.data + 1;
    }

    createAdminProduct(data, productName, price);

    System.out.println("\nProduct added successfully!");
}

 static void updateAdminProduct() {
    Scanner scanner = new Scanner(System.in);

    displayAdminProductList();
    System.out.print("Enter product ID to update: ");
    int productId = scanner.nextInt();

    ProductNode temp = headAdmin;
    boolean found = false;

    while (temp != null) {
        if (temp.data == productId) {
            found = true;
            System.out.print("\nEnter new product name: ");
            String newProductName = scanner.next();
            System.out.print("Enter new product price: ");
            float newPrice = scanner.nextFloat();

            temp.productName = newProductName;
            temp.price = newPrice;

            System.out.println("\nProduct updated successfully!");
            break;
        }
        temp = temp.next;
    }

    if (!found) {
        System.out.println("\nProduct not found!");
    }
}

 static void deleteAdminProduct() {
    Scanner scanner = new Scanner(System.in);

    displayAdminProductList();
    System.out.print("Enter product ID to delete: ");
    int productId = scanner.nextInt();

    ProductNode temp = headAdmin;
    ProductNode prev = null;
    boolean found = false;

    while (temp != null) {
        if (temp.data == productId) {
            found = true;
            if (prev == null) {
                headAdmin = temp.next;
            } else {
                prev.next = temp.next;
            }
            System.out.println("\nProduct deleted successfully!");
            break;
        }
        prev = temp;
        temp = temp.next;
    }

    if (!found) {
        System.out.println("\nProduct not found!");
    }
}

 static class Edge {

        String source;
        String destination;
        int weight;

        Edge(String source, String destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

static void dfs(ArrayList<Edge>[] graph, String current, String target, int currentWeight, ArrayList<String> path, ArrayList<ArrayList<String>> result, ArrayList<Integer> weights) {
        path.add(current);

        if (current.equals(target)) {
            result.add(new ArrayList<>(path));
            weights.add(currentWeight);
        } else {
            for (Edge edge : graph[getIndex(graph, current)]) {
                String neighbor = edge.destination;
                int edgeWeight = edge.weight;

                if (!path.contains(neighbor)) {
                    dfs(graph, neighbor, target, currentWeight + edgeWeight, path, result, weights);
                }
            }
        }

        path.remove(path.size() - 1);
    }

 static int getIndex(ArrayList<Edge>[] graph, String vertex) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].get(0).source.equals(vertex)) {
                return i;
            }
        }
        return -1; // Vertex not found
    }
static void createWeightedGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge("ecb", "mirpur14", 2));
        graph[0].add(new Edge("ecb", "kalshi", 1));

        graph[1].add(new Edge("kalshi", "ecb", 1));
        graph[1].add(new Edge("kalshi", "dohs", 3));
        graph[1].add(new Edge("kalshi", "mirpur10", 2));

        graph[2].add(new Edge("mirpur14", "ecb", 2));
        graph[2].add(new Edge("mirpur14", "mirpur10", 1));

        graph[3].add(new Edge("dohs", "kalshi", 3));

        graph[4].add(new Edge("mirpur10", "kalshi", 2));
        graph[4].add(new Edge("mirpur10", "mirpur14", 1));
    }
 static void Shortpath(String des) {
        ArrayList<Edge>[] weightedGraph = new ArrayList[5];
        createWeightedGraph(weightedGraph);

        String sourceVertex = "ecb";
        String targetVertex = des;

        ArrayList<ArrayList<String>> allPaths = new ArrayList<>();
        ArrayList<Integer> pathWeights = new ArrayList<>();

        dfs(weightedGraph, sourceVertex, targetVertex, 0, new ArrayList<>(), allPaths, pathWeights);

        for (int i = 0; i < allPaths.size(); i++) {
            System.out.println("\t\tRoute " + (i + 1) + ": " + allPaths.get(i) + "Distance: " + pathWeights.get(i));
        }
    }
  
 
    static ProductNode findMid(ProductNode head) {
        ProductNode slow = head;
        ProductNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
 
 static ProductNode mergesort(ProductNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ProductNode mid = findMid(head);
        ProductNode left = head;
        ProductNode right = mid.next;
        mid.next = null;

        left = mergesort(left);
        right = mergesort(right);

        ProductNode result = Merge(left, right);

        return result;
    }

    static ProductNode Merge(ProductNode left, ProductNode right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        ProductNode ans = new ProductNode(-1, "",-1);
    //    ans.data = -1;
        ProductNode temp = ans;

        while (left != null && right != null) {
            if (left.price < right.price) {
                temp.next = left;
                left = left.next;
                temp = temp.next;
            } else {
                temp.next = right;

                right = right.next;
                temp = temp.next;

            }

        }
        while (left != null) {
            temp.next = left;
            left = left.next;
            temp = temp.next;
        }
        while (right != null) {
            temp.next = right;

            right = right.next;
            temp = temp.next;
        }

        return ans.next;
    }
 
 }

