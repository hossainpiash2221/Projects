/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package blooddonate5;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.PriorityQueue;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Donor {

    int rno;
    String name;
    int age;
    String mobile;
    String address;
    String bloodgroup;
    String lastdonet;
    String distance;
    int distance2;
    Donor next;

    int compareTo(Donor pivot) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

public class Blooddonate5 {

    static Donor head = null;
    static Donor head2 = null;
    static Donor rear = null;
    static Donor front = null;
    static Donor recycleBin = null;
    static Scanner scanner = new Scanner(System.in);

    static int dijkstra(ArrayList<Edge>[] graph, String start, String source) {
        int n = graph.length;
        int[] distance = new int[n];
        boolean[] visited = new boolean[n];

        PriorityQueue<Edge> minHeap = new PriorityQueue<>((a, b) -> a.weight - b.weight);

        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[Index(graph, start)] = 0;
        minHeap.add(new Edge(start, start, 0));

        while (!minHeap.isEmpty()) {
            Edge current = minHeap.poll();

            if (visited[Index(graph, current.destination)]) {
                continue;
            }

            visited[Index(graph, current.destination)] = true;

            for (Edge neighbor : graph[Index(graph, current.destination)]) {
                int newDistance = distance[Index(graph, current.destination)] + neighbor.weight;

                if (newDistance < distance[Index(graph, neighbor.destination)]) {
                    distance[Index(graph, neighbor.destination)] = newDistance;
                    minHeap.add(new Edge(neighbor.source, neighbor.destination, newDistance));
                }
            }
        }

        // Print the shortest distance from the source node to the specified node
        return distance[Index(graph, source)];
    }

    static int Index(ArrayList<Edge>[] graph, String node) {
        for (int i = 0; i < graph.length; i++) {
            //  if (graph[i].get(0).source.equals(node)) {
            if (node.contains(graph[i].get(0).source)) {
                return i;
            } else if (graph[i].get(0).source.contains(node)) {
                return i;
            }

        }
        return -1;
    }

    static void findNearestDonor() {
        Scanner scanner = new Scanner(System.in);
        int numNodes = 11; // Change this value based on the actual number of nodes in your graph
        ArrayList<Edge>[] graph = new ArrayList[numNodes];
        createWeightedGraph(graph);

//        System.out.print("\n\n\tEnter Blood Group: ");
//        String targetBloodGroup = scanner.nextLine();
        //     ArrayList<Edge>[] weightedGraph = new ArrayList[5];
        //    createWeightedGraph(weightedGraph);
        Donor temp = head;
        Donor head2 = null;
        ArrayList<Donor> matchingDonors = new ArrayList<>();

        // Find donors with the same blood group
        while (temp != null) {
//            if (temp.bloodgroup.equalsIgnoreCase(targetBloodGroup)) {
            matchingDonors.add(temp);

            String startNode = "ecb"; // Change this value based on the starting node
            String sourceNode = temp.address; // Change this value based on the source node

            temp.distance2 = dijkstra(graph, startNode, sourceNode);

            //           }
            temp = temp.next;
        }

        head = mergesort(head);

        searchdonor3();

    }

    static void displaydonor2(Donor temp) {

        printfline();
        System.out.printf("|%-8s|%-20s|%-12s|%-15s|%-25s|%-20s|%-20s|%-12s|\n",
                "SERIAL", "NAME", "AGE", "MOBILE NUMBER", "ADDRESS", "BLOOD GROUP", "LAST DONATED", "DISTANCE");
        printfline();
        while (temp != null) {

            System.out.print("|" + String.format("%-8d", temp.rno));
            System.out.print("|" + String.format("%-20s", temp.name));
            System.out.print("|" + String.format("%d Years    ", temp.age));
            System.out.print("|" + String.format("%-15s", temp.mobile));
            System.out.print("|" + String.format("%-25s", temp.address));
            System.out.print("|" + String.format("%-20s", temp.bloodgroup));
            System.out.print("|" + String.format("%-20s", temp.lastdonet));
            System.out.print("|" + String.format("%-12s", temp.distance));

            temp = temp.next;
            System.out.println("");
        }
        System.out.println("");
        printfline();
    }

    static Donor findMid(Donor head) {
        Donor slow = head;
        Donor fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    static Donor mergesort(Donor head) {
        if (head == null || head.next == null) {
            return head;
        }
        Donor mid = findMid(head);
        Donor left = head;
        Donor right = mid.next;
        mid.next = null;

        left = mergesort(left);
        right = mergesort(right);

        Donor result = Merge(left, right);

        return result;
    }

    static Donor Merge(Donor left, Donor right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        Donor ans = new Donor();
        ans.rno = -1;
        Donor temp = ans;

        while (left != null && right != null) {
            if (left.distance2 < right.distance2) {
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

    public static class Edge {

        String source;
        String destination;
        int weight;

        Edge(String source, String destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static void createWeightedGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge("ecb", "mirpur14", 4));
        graph[0].add(new Edge("ecb", "kalshi", 2));
        graph[0].add(new Edge("ecb", "kuril", 4));

        graph[1].add(new Edge("kalshi", "ecb", 2));
        graph[1].add(new Edge("kalshi", "dohs", 2));
        graph[1].add(new Edge("kalshi", "mirpur10", 3));
        graph[1].add(new Edge("kalshi", "mirpur12", 3));

        graph[2].add(new Edge("mirpur14", "ecb", 4));
        graph[2].add(new Edge("mirpur14", "mirpur10", 3));
        graph[2].add(new Edge("mirpur14", "farmgate", 6));

        graph[3].add(new Edge("dohs", "kalshi", 2));

        graph[4].add(new Edge("mirpur10", "kalshi", 3));
        graph[4].add(new Edge("mirpur10", "mirpur14", 3));
        graph[4].add(new Edge("mirpur10", "mirpur12", 3));
        graph[4].add(new Edge("mirpur10", "shewrapara", 4));

        graph[5].add(new Edge("mirpur12", "shemoli", 9));
        graph[5].add(new Edge("mirpur12", "kalshi", 3));
        graph[5].add(new Edge("mirpur12", "mirpur10", 3));

        graph[6].add(new Edge("shemoli", "shewrapara", 5));
        graph[6].add(new Edge("shemoli", "mirpur12", 9));

        graph[7].add(new Edge("shewrapara", "mirpur10", 4));
        graph[7].add(new Edge("shewrapara", "shemoli", 5));
        graph[7].add(new Edge("shewrapara", "farmgate", 5));

        graph[8].add(new Edge("farmgate", "shewrapara", 5));
        graph[8].add(new Edge("farmgate", "mirpur14", 6));
        graph[8].add(new Edge("farmgate", "kuril", 10));

        graph[9].add(new Edge("kuril", "ecb", 4));
        graph[9].add(new Edge("kuril", "uttara", 11));
        graph[9].add(new Edge("kuril", "farmgate", 10));

        graph[10].add(new Edge("uttara", "kuril", 10));
    }

    static void dfs(ArrayList<Edge>[] graph, String current, String target, int currentWeight, ArrayList<String> path, ArrayList<ArrayList<String>> result, ArrayList<Integer> weights) {
        path.add(current);

        // if (current.equals(target)) 
        if (target.contains(current)) {
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
            //  if (graph[i].get(0).source.equals(vertex)) 
            if (vertex.contains(graph[i].get(0).source)) {
                return i;
            } else if (graph[i].get(0).source.contains(vertex)) {
                return i;
            }
        }
        return -1; // Vertex not found
    }

    static void Shortpath(String des) {
        ArrayList<Edge>[] weightedGraph = new ArrayList[11];
        createWeightedGraph(weightedGraph);

        String sourceVertex = "ecb";
        String targetVertex = des;

        ArrayList<ArrayList<String>> allPaths = new ArrayList<>();
        ArrayList<Integer> pathWeights = new ArrayList<>();

        dfs(weightedGraph, sourceVertex, targetVertex, 0, new ArrayList<>(), allPaths, pathWeights);

        for (int i = 0; i < allPaths.size(); i++) {
            System.out.println("\t\tRoute " + (i + 1) + ": " + allPaths.get(i) + ", Total Weight: " + pathWeights.get(i));
        }
    }

    static void enqueue() {

        Donor newdonor = new Donor();
        newdonor.next = null;
        scanner.nextLine();
        System.out.print("\n\n\tDonor name: ");
        newdonor.name = scanner.nextLine();
        scanner.nextLine();
        System.out.print("\n\n\tAge: ");
        newdonor.age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("\n\n\tMobile Number: ");
        newdonor.mobile = scanner.nextLine();
        System.out.print("\n\n\tAddress: ");
        newdonor.address = scanner.nextLine();
        System.out.print("\n\n\tBlood group: ");
        newdonor.bloodgroup = scanner.nextLine();
        System.out.print("\n\n\tLast Donated Date: ");
        newdonor.lastdonet = scanner.nextLine();
        System.out.print("\n\n\tDistance: ");
        newdonor.distance = scanner.nextLine();

        if (front == null && rear == null) {
            front = rear = newdonor;
        } else {
            rear.next = newdonor;
            rear = newdonor;
        }
    }

    static void printqueue() {
        int numNodes = 11; // Change this value based on the actual number of nodes in your graph
        ArrayList<Edge>[] graph = new ArrayList[numNodes];
        createWeightedGraph(graph);

        Donor temp = front;
        printfline();
        System.out.printf("|%-8s|%-20s|%-12s|%-15s|%-25s|%-20s|%-20s|%-12s|\n",
                "SERIAL", "NAME", "AGE", "MOBILE NUMBER", "ADDRESS", "BLOOD GROUP", "LAST DONATED", "DISTANCE");
        printfline();
        while (temp != null) {
            //   scanner.nextLine();
            // System.out.printf("|%-8d|%-20s|%d Years    |%-15s|%-25s|%-20s|%-20s|%-12s|\n",
            //3       temp.rno, temp.name, temp.age, temp.mobile, temp.address, temp.bloodgroup, temp.lastdonet, temp.distance);
            String startNode = "ecb"; // Change this value based on the starting node
            String sourceNode = temp.address; // Change this value based on the source node

            int dis = dijkstra(graph, startNode, sourceNode);
            temp.distance = Integer.toString(dis);
            System.out.print("|" + String.format("%-8d", temp.rno));
            System.out.print("|" + String.format("%-20s", temp.name));
            System.out.print("|" + String.format("%d Years    ", temp.age));
            System.out.print("|" + String.format("%-15s", temp.mobile));
            System.out.print("|" + String.format("%-25s", temp.address));
            System.out.print("|" + String.format("%-20s", temp.bloodgroup));
            System.out.print("|" + String.format("%-20s", temp.lastdonet));
            System.out.print("|" + String.format("%-12s", temp.distance));

            temp = temp.next;
            System.out.println("");
        }
        System.out.println("");
        printfline();
    }

    static void dequeue() {

        int numNodes = 11; // Change this value based on the actual number of nodes in your graph
        ArrayList<Edge>[] graph = new ArrayList[numNodes];
        createWeightedGraph(graph);
        if (front == null) {
            System.out.println("\nQUEUE EMPTY");
        } else {

            Donor temp = front;

            printfline();
            System.out.printf("|%-8s|%-20s|%-12s|%-15s|%-25s|%-20s|%-20s|%-12s|\n",
                    "SERIAL", "NAME", "AGE", "MOBILE NUMBER", "ADDRESS", "BLOOD GROUP", "LAST DONATED", "DISTANCE");
            printfline();

            String startNode = "ecb"; // Change this value based on the starting node
            String sourceNode = temp.address; // Change this value based on the source node

            int dis = dijkstra(graph, startNode, sourceNode);
            temp.distance = Integer.toString(dis);
            System.out.print("|" + String.format("%-8d", temp.rno));
            System.out.print("|" + String.format("%-20s", temp.name));
            System.out.print("|" + String.format("%d Years    ", temp.age));
            System.out.print("|" + String.format("%-15s", temp.mobile));
            System.out.print("|" + String.format("%-25s", temp.address));
            System.out.print("|" + String.format("%-20s", temp.bloodgroup));
            System.out.print("|" + String.format("%-20s", temp.lastdonet));
            System.out.print("|" + String.format("%-12s", temp.distance));

            temp = temp.next;
            System.out.println("");

            front = front.next;
            if (front == null) {
                rear = null;
            }
        }
    }

    static void push(Donor del) {
        if (recycleBin == null) {
            del.next = null;
            recycleBin = del;
        } else {
            del.next = recycleBin;
            recycleBin = del;
        }
    }

    static void push2(Donor del) {
        if (head == null) {
            del.next = null;
            head = del;
        } else {
            del.next = head;
            head = del;
        }
    }

    static void pop() {
        if (recycleBin == null) {
            // Linked list is empty, nothing to pop
            System.out.println("\n\t\tEmpty");
        } else {
            Donor poppedDonor = recycleBin;
            recycleBin = recycleBin.next;
            poppedDonor.next = null; // Remove reference to the next node

            push2(poppedDonor);
        }
    }

    static void printfline() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
    }

    static void adddonor() {
        boolean found=true;
        
        while(found){
        
        String mb="01";
        
        Donor newdonor = new Donor();
        newdonor.next = null;
        scanner.nextLine();
        System.out.print("\n\n\tDonor name: ");
        newdonor.name = scanner.nextLine();
        scanner.nextLine();
        System.out.print("\n\n\tAge: ");
        newdonor.age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("\n\n\tMobile Number: ");
        newdonor.mobile = scanner.nextLine();
        
        int len=newdonor.mobile.length();
        if(!KMPSearch(mb, newdonor.mobile))
        {
            System.out.println("\n\n\tInvalid Phone nUmber");
            
            System.out.println("\n\n\tPleas give me your information correctly.");
            
            continue;
        }
        if(len!=11)
        {
             System.out.println("\n\n\tInvalid Phone nUmber");
            
            System.out.println("\n\n\tPleas give me your information correctly.");
            
            continue;
        }
       
            
        
        
        System.out.print("\n\n\tAddress: ");
        newdonor.address = scanner.nextLine();
        System.out.print("\n\n\tBlood group: ");
        newdonor.bloodgroup = scanner.nextLine();
        System.out.print("\n\n\tLast Donated Date: ");
        newdonor.lastdonet = scanner.nextLine();
        System.out.print("\n\n\tDistance: ");
        newdonor.distance = scanner.nextLine();
        if (head == null) {
            newdonor.rno = 1;
            head = newdonor;
            head.next = null;

        } else {
            Donor temp = head;
            int serialno = 1;
            while (temp.next != null) {
                temp = temp.next;
                serialno++;
            }
            newdonor.rno = serialno + 1;
            temp.next = newdonor;
            temp.next.next = null;
        }
        found =false;
      }
    }

    static void displaydonor() {

        int numNodes = 11; // Change this value based on the actual number of nodes in your graph
        ArrayList<Edge>[] graph = new ArrayList[numNodes];
        createWeightedGraph(graph);

        Donor temp = head;
        printfline();
        System.out.printf("|%-8s|%-20s|%-12s|%-15s|%-25s|%-20s|%-20s|%-12s|\n",
                "SERIAL", "NAME", "AGE", "MOBILE NUMBER", "ADDRESS", "BLOOD GROUP", "LAST DONATED", "DISTANCE");
        printfline();
        while (temp != null) {
            //   scanner.nextLine();
            // System.out.printf("|%-8d|%-20s|%d Years    |%-15s|%-25s|%-20s|%-20s|%-12s|\n",
            //3       temp.rno, temp.name, temp.age, temp.mobile, temp.address, temp.bloodgroup, temp.lastdonet, temp.distance);
            String startNode = "ecb"; // Change this value based on the starting node
            String sourceNode = temp.address; // Change this value based on the source node

            int dis = dijkstra(graph, startNode, sourceNode);
            temp.distance = Integer.toString(dis);
            System.out.print("|" + String.format("%-8d", temp.rno));
            System.out.print("|" + String.format("%-20s", temp.name));
            System.out.print("|" + String.format("%d Years    ", temp.age));
            System.out.print("|" + String.format("%-15s", temp.mobile));
            System.out.print("|" + String.format("%-25s", temp.address));
            System.out.print("|" + String.format("%-20s", temp.bloodgroup));
            System.out.print("|" + String.format("%-20s", temp.lastdonet));
            System.out.print("|" + String.format("%-12s", temp.distance));

            temp = temp.next;
            System.out.println("");
        }
        System.out.println("");
        printfline();
    }

    static void insertdonor() {
        Donor temp = head;
        int serialno;
        String name, mobile, address, bloodgroup, lastdonated, distance;
        int age;
        System.out.print("\n\n\tInsert at Position: ");
        serialno = scanner.nextInt();
        scanner.nextLine();
        Donor newdonor = new Donor();
        System.out.print("\n\n\tDonor name: ");
        name = scanner.nextLine();
        newdonor.name = name;
        System.out.print("\n\n\tAge: ");
        age = scanner.nextInt();
        scanner.nextLine();
        newdonor.age = age;
        System.out.print("\n\n\tMobile Number: ");
        mobile = scanner.nextLine();
        newdonor.mobile = mobile;
        System.out.print("\n\n\tAddress: ");
        address = scanner.nextLine();
        newdonor.address = address;
        System.out.print("\n\n\tBlood group: ");
        bloodgroup = scanner.nextLine();
        newdonor.bloodgroup = bloodgroup;
        System.out.print("\n\n\tLast Donated Date: ");
        lastdonated = scanner.nextLine();
        newdonor.lastdonet = lastdonated;
        System.out.print("\n\n\tDistance: ");
        distance = scanner.nextLine();
        newdonor.distance = distance;
        newdonor.rno = serialno;
        if (head == null) {
            newdonor.next = null;
            head = newdonor;
        } else {
            int i = 1;
            while (temp.rno < serialno) {
                if (temp.next == null) {
                    newdonor.next = null;
                    temp.next = newdonor;
                    return;
                } else if (temp.next.rno > serialno) {
                    newdonor.next = temp.next;
                    temp.next = newdonor;
                    return;
                }
                i++;
                temp = temp.next;
            }
            newdonor.next = temp.next;
            temp.next = newdonor;
        }
    }

    static void searchdonor3() {
        boolean found = false;
        Donor temp = head;
        String Bgroup;
        scanner.nextLine();
        System.out.print("\n\n\tSearch by Blood Group: ");
        Bgroup = scanner.nextLine();
        printfline();
        System.out.printf("|%-8s|%-20s|%-12s|%-15s|%-25s|%-20s|%-20s|%-12s|\n",
                "SERIAL", "NAME", "AGE", "MOBILE NUMBER", "ADDRESS", "BLOOD GROUP", "LAST DONATED", "DISTANCE");
        printfline();
        while (temp != null) {
            boolean result = temp.bloodgroup.equalsIgnoreCase(Bgroup);
            boolean result2 = temp.bloodgroup.contains(Bgroup);
            if (result || result2) {

                System.out.printf("|%-8d|%-20s|%d Years    |%-15s|%-25s|%-20s|%-20s|%-12s|\n",
                        temp.rno, temp.name, temp.age, temp.mobile, temp.address, temp.bloodgroup, temp.lastdonet, temp.distance);
                System.out.println("");
                found = true;

                printfline();

            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("\t\t\tDonor not found!");
        }
        printfline();
    }

    static void searchdonor() {
        boolean found = false;
        Donor temp = head;
        String Bgroup;
        scanner.nextLine();
        System.out.print("\n\n\tSearch by Blood Group: ");
        Bgroup = scanner.nextLine();
        printfline();
        System.out.printf("|%-8s|%-20s|%-12s|%-15s|%-25s|%-20s|%-20s|%-12s|\n",
                "SERIAL", "NAME", "AGE", "MOBILE NUMBER", "ADDRESS", "BLOOD GROUP", "LAST DONATED", "DISTANCE");
        printfline();
        while (temp != null) {
            boolean result = temp.bloodgroup.equalsIgnoreCase(Bgroup);
            boolean result2 = temp.bloodgroup.contains(Bgroup);
            if (result || result2) {

                System.out.printf("|%-8d|%-20s|%d Years    |%-15s|%-25s|%-20s|%-20s|%-12s|\n",
                        temp.rno, temp.name, temp.age, temp.mobile, temp.address, temp.bloodgroup, temp.lastdonet, temp.distance);
                System.out.println("");
                found = true;

                Shortpath(temp.address);

                printfline();

            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("\t\t\tDonor not found!");
        }
        printfline();
    }

    static void updatedonor() {
        Donor temp = head;
        int serialno;
        String name, mobile, address, bloodgroup, lastdonated, distance;
        int age;
        System.out.print("\n\n\tUpdate Serial Number: ");
        serialno = scanner.nextInt();
        while (temp != null) {
            if (temp.rno == serialno) {
                System.out.print("\n\n\tDonor name: ");
                scanner.nextLine();
                name = scanner.nextLine();
                temp.name = name;
                System.out.print("\n\n\tAge: ");
                age = scanner.nextInt();
                temp.age = age;
                scanner.nextLine();
                System.out.print("\n\n\tMobile Number: ");
                mobile = scanner.nextLine();
                temp.mobile = mobile;
                System.out.print("\n\n\tAddress: ");
                address = scanner.nextLine();
                temp.address = address;
                System.out.print("\n\n\tBlood group: ");
                bloodgroup = scanner.nextLine();
                temp.bloodgroup = bloodgroup;
                System.out.print("\n\n\tLast Donated Date: ");
                lastdonated = scanner.nextLine();
                temp.lastdonet = lastdonated;
                System.out.print("\n\n\tDistance: ");
                distance = scanner.nextLine();
                temp.distance = distance;
                return;
            }
            temp = temp.next;
        }
        System.out.println("Donor not found!");
    }

    static void deletedonor() {
        Donor temp = head;
        int serialno;
        System.out.print("\n\n\tDelete Serial Number: ");
        serialno = scanner.nextInt();
        if (temp != null && temp.rno == serialno) {
            head = temp.next;
            push(temp);
            return;
        }
        Donor prev = null;
        while (temp != null && temp.rno != serialno) {
            prev = temp;
            temp = temp.next;

        }
        if (temp == null) {
            System.out.println("Donor not found!");
            return;
        }
        prev.next = temp.next;
        push(temp);
    }

    static void deletedonor2() {
        recycleBin = null;
    }

    static void display2() {
        Donor temp = recycleBin;
        printfline();
        System.out.printf("|%-8s|%-20s|%-12s|%-15s|%-25s|%-20s|%-20s|%-12s|\n",
                "SERIAL", "NAME", "AGE", "MOBILE NUMBER", "ADDRESS", "BLOOD GROUP", "LAST DONATED", "DISTANCE");
        printfline();
        while (temp != null) {
            System.out.printf("|%-8d|%-20s|%d Years    |%-15s|%-25s|%-20s|%-20s|%-12s|\n",
                    temp.rno, temp.name, temp.age, temp.mobile, temp.address, temp.bloodgroup, temp.lastdonet, temp.distance);
            temp = temp.next;
        }
        printfline();
    }

    static void searchdonor2() {
        boolean found = false;
        Donor temp = head;
        String searchQuery;

        // Clearing input buffer
        scanner.nextLine();

        System.out.print("\n\n\tSearch Donor by Name or Address: ");
        searchQuery = scanner.nextLine();

        printfline();
        System.out.printf("|%-8s|%-20s|%-12s|%-15s|%-25s|%-20s|%-20s|%-12s|\n",
                "SERIAL", "NAME", "AGE", "MOBILE NUMBER", "ADDRESS", "BLOOD GROUP", "LAST DONATED", "DISTANCE");
        printfline();

        while (temp != null) {
            // Concatenate name and address for searching
            String donorInfo = temp.name + " " + temp.address;

            // Use KMPSearch to find matches in donor names or addresses
            if (KMPSearch(searchQuery, donorInfo)) {

                // Display matched donor information
                System.out.printf("|%-8d|%-20s|%d Years    |%-15s|%-25s|%-20s|%-20s|%-12s|\n",
                        temp.rno, temp.name, temp.age, temp.mobile, temp.address, temp.bloodgroup, temp.lastdonet, temp.distance);

                // Set found flag to true if a match is found
                found = true;
            }

            temp = temp.next;
        }

        // Show message if no donors are found
        if (!found) {
            System.out.println("\t\t\tDonor not found!");
        }

        printfline();
    }

    static boolean KMPSearch(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        int[] lps = new int[M];
        int j = 0;

        computeLPSArray(pat, M, lps);

        int i = 0;
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {

                // System.out.println("Found pattern at index " + (i - j));
                j = lps[j - 1];
                return true;

            } else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }
        return false;
    }

    static void computeLPSArray(String pat, int M, int lps[]) {
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.println("\t\tBlood Donating System.\n");
        System.out.println("Give your Information First.");
        adddonor();
        int ch;
        do {
            System.out.println("\n\n\t\tMain Menu\n");
            System.out.println("\n\t1. Blood Required");
            System.out.println("\n\t2. Find Nearest Donor.");
            System.out.println("\n\t3. Add Donor");
            System.out.println("\n\t4. Display Donors");
            System.out.println("\n\t5. Search donor");
            System.out.println("\n\t6. Search blood group");
            System.out.println("\n\t7. Update Donor");
            System.out.println("\n\t8. Delete Donor");
            System.out.println("\n\t9. Display Recycle Bin");
            System.out.println("\n\t10. Exit");
            System.out.print("\n\n\tPlease enter your choice:");
            ch = scanner.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("\n\n\t 1. Blood Request.");
                    System.out.println("\n\n\t 2. Accepted.");
                    System.out.println("\n\n\t 3. Show All Requested.");
                    System.out.print("\n\n\tPlease enter your choice:");
                    int num = scanner.nextInt();
                    if (num == 1) {
                        printqueue();
                        enqueue();

                    } else if (num == 2) {
                        dequeue();
                    } else if (num == 3) {
                        printqueue();
                    }
                    // herer use blood request and accept request. when accept the request first request one remove from front
                    break;
                case 2:
                    findNearestDonor();
                    break;
                case 3:

                    adddonor();
                    break;
                case 4:
                    displaydonor();
                    break;
                case 5:
                    searchdonor2();
                    break;
                case 6:
                    searchdonor();
                    break;
                case 7:
                    updatedonor();
                    break;
                case 8:
                    deletedonor();
                    break;
                case 9:
                    display2();
                    System.out.println("\n\n\t 1. Restore.");
                    System.out.println("\n\n\t 2. Clear.");
                    System.out.println("\n\n\t 3. Back.");
                    System.out.print("\n\n\tPlease enter your choice:");
                    int num2 = scanner.nextInt();
                    if (num2 == 1) {
                        pop();
                    } else if (num2 == 2) {
                        deletedonor2();
                    }

                    break;
                case 10:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
                    break;
            }
        } while (ch != 10);
    }
}
