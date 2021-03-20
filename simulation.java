import java.util.*;
import java.lang.*;
class Node{
	String add;
	String data;
	int access;
	Node(String x, String y,int z){
		add=x;
		data=y;
		access=z;
	}
}
public class Dipankar_2019037_FinalAssignment{
	public static void dmap(int Clines,int B){
		Node[] tag= new Node[(int)Math.pow((double)2,(double)Clines)];//contain the memory adresses
		//int[] size=new int[(int)Math.pow((double)2,(double)Clines-K)];
		for(int i=0;i<(int)Math.pow((double)2,(double)Clines);i++){
			tag[i]=new Node("empty","null",0);
		}
		
		Scanner in=new Scanner(System.in);
		int indexlen=Clines;
		System.out.println("No. of querries");
		int N=in.nextInt();
		for(int i=0;i<N;i++){
			System.out.println("1 for reading, 2 for writing ");
			int rw=in.nextInt();
			if(rw==1){
				System.out.println("Give memory address to read (16 bit) ");
				String add1=in.next();
				String tagadd=add1.substring(0,16-B);//what is stored in tag(address of a block)
				int index=Integer.parseInt(add1.substring(16-indexlen-B,16-B),2);//cache address index
				
				//int k=(int)Math.pow((double)2,(double)K);
				
				int c=0;//to check hit/miss
				if(tag[index].add.equals(tagadd)){
						System.out.println("HIT, data: "+tag[index].data);
						tag[index].access+=1;
				}
				else{
					tag[index].add=tagadd;
					tag[index].data="empty";
					System.out.println("MISS, replacing with memory address and data: "+tag[index].add+" "+tag[index].data);
					tag[index].access=1;
				}
				
			
		}
			else{
				System.out.println("Give memory address to write (16 bit) ");
				String add1=in.next();
				System.out.println("Give data to insert ");
				String data1=in.next();
				String tagadd=add1.substring(0,16-B);//what is stored in tag(address of a block)
				int index=Integer.parseInt(add1.substring(16-indexlen-B,16-B),2);//cache address index
					if(tag[index].add.equals(tagadd)){
						tag[index].data=data1;
						System.out.println("HIT, new data: "+tag[index].data);
						tag[index].access+=1;
					}					
					else{
						tag[index].add=tagadd;
						tag[index].data=data1;
						tag[index].access=1;
						System.out.println("MISS, replacing with memory adress and data: "+tag[index].add+" "+tag[index].data);
					}
				
			}
		}
	}
	public static void assoc(int Clines,int B){
		Node[] tag= new Node[(int)Math.pow((double)2,(double)Clines)];//contain the memory adresses
		int size=0;
		
		Scanner in=new Scanner(System.in);
		//int indexlen=Clines-K;
		System.out.println("No. of querries");
		int N=in.nextInt();
		for(int i=0;i<N;i++){
			System.out.println("1 for reading, 2 for writing ");
			int rw=in.nextInt();
			if(rw==1){
				System.out.println("Give memory address to read (16 bit) ");
				String add1=in.next();
				String tagadd=add1.substring(0,16-B);//what is stored in tag(address of a block)
				//int index=Integer.parseInt(add1.substring(16-indexlen-B,16-B),2);//cache address index
				
				int k=(int)Math.pow((double)2,(double)Clines);
				
				int c=0;//to check hit/miss
				
				for(int j=0;j<size;j++){
					if(tag[j].add.equals(tagadd)){
						System.out.println("HIT, data: "+tag[j].data);
						tag[j].access+=1;
						c=1;
						break;
					}
				}
				if(c==0){
					if(size<k){
						//System.out.println("here");
						int mmemory_add=Integer.parseInt(add1.substring(0,16-B),2);
						tag[size]=new Node(tagadd,"empty",1);
						System.out.println("MISS, replacing with memory address and data: "+tag[size].add+" "+tag[size].data);
						size+=1;

					}
					else{
						int mmemory_add=Integer.parseInt(add1.substring(0,16-B),2);
						int min=tag[0].access;
						int temp=0;
						for(int j=1;j<=size;j++){
							if(tag[j].access<min){
								temp=j;
							}
						}
						tag[temp].add=tagadd;
						tag[temp].data="empty";
						System.out.println("MISS, replacing with memory address and data: "+tag[temp].add+" "+tag[temp].data);
						tag[temp].access=1;
						//size[index]+=0;//already full
					}
				}
			
		}
			else{
				System.out.println("Give memory address to write (16 bit) ");
				String add1=in.next();
				System.out.println("Give data to insert ");
				String data1=in.next();
				String tagadd=add1.substring(0,16-B);//what is stored in tag(address of a block)
				//int index=Integer.parseInt(add1.substring(16-indexlen-B,16-B),2);//cache address index
				int k=(int)Math.pow((double)2,(double)Clines);
				int c=0;//to check hit/miss
				for(int j=0;j<size;j++){
					if(tag[j].add.equals(tagadd)){
						tag[j].data=data1;
						System.out.println("HIT, new data: "+tag[j].data);
						tag[j].access+=1;
						c=1;
						break;
					}
				}
				if(c==0){
					if(size<k){
						int mmemory_add=Integer.parseInt(add1.substring(0,16-B),2);
						tag[size]=new Node(tagadd,data1,1);
						System.out.println("MISS, replacing with memory address and data: "+tag[size].add+" "+tag[size].data);
						size+=1;

					}
					else{
						int mmemory_add=Integer.parseInt(add1.substring(0,16-B),2);
						int min=tag[0].access;
						int temp=0;
						for(int j=1;j<=(k-1);j++){
							if(tag[j].access<min){
								temp=j;
							}
						}
						tag[temp].add=tagadd;
						tag[temp].data=data1;
						tag[temp].access=1;
						System.out.println("MISS, replacing with memory adress and data: "+tag[temp].add+" "+tag[temp].data);
						//size[index]+=0;//already full
					}
				}
			}
		}
	}
	
	
	
	public static void k_way(int Clines,int B,int K){
		Node[] tag= new Node[(int)Math.pow((double)2,(double)Clines)];//contain the memory adresses
		int[] size=new int[(int)Math.pow((double)2,(double)Clines-K)];
		for(int i=0;i<(int)Math.pow((double)2,(double)Clines-K);i++){
			size[i]=0;
		}
		
		Scanner in=new Scanner(System.in);
		int indexlen=Clines-K;
		System.out.println("No. of querries");
		int N=in.nextInt();
		for(int i=0;i<N;i++){
			System.out.println("1 for reading, 2 for writing ");
			int rw=in.nextInt();
			if(rw==1){
				System.out.println("Give memory address to read (16 bit) ");
				String add1=in.next();
				String tagadd=add1.substring(0,16-B);//what is stored in tag(address of a block)
				int index=Integer.parseInt(add1.substring(16-indexlen-B,16-B),2);//cache address index
				
				int k=(int)Math.pow((double)2,(double)K);
				
				int c=0;//to check hit/miss
				
				for(int j=index*k;j<(index*k)+size[index];j++){
					if(tag[j].add.equals(tagadd)){
						System.out.println("HIT, data: "+tag[j].data);
						tag[j].access+=1;
						c=1;
						break;
					}
				}
				if(c==0){
					if(size[index]<k){
						System.out.println("here");
						int mmemory_add=Integer.parseInt(add1.substring(0,16-B),2);
						tag[index*k+size[index]]=new Node(tagadd,"empty",1);
						System.out.println("MISS, replacing with memory address and data: "+tag[index*k+size[index]].add+" "+tag[index*k+size[index]].data);
						size[index]+=1;

					}
					else{
						int mmemory_add=Integer.parseInt(add1.substring(0,16-B),2);
						int min=tag[index*k].access;
						int temp=index*k;
						for(int j=index*k+1;j<=(index*k)+(k-1);j++){
							if(tag[j].access<min){
								temp=j;
							}
						}
						tag[temp].add=tagadd;
						tag[temp].data="empty";
						System.out.println("MISS, replacing with memory address and data: "+tag[temp].add+" "+tag[temp].data);
						tag[temp].access=1;
						size[index]+=0;//already full
					}
				}
			
		}
			else{
				System.out.println("Give memory address to write (16 bit) ");
				String add1=in.next();
				System.out.println("Give data to insert ");
				String data1=in.next();
				String tagadd=add1.substring(0,16-B);//what is stored in tag(address of a block)
				int index=Integer.parseInt(add1.substring(16-indexlen-B,16-B),2);//cache address index
				int k=(int)Math.pow((double)2,(double)K);
				int c=0;//to check hit/miss
				for(int j=index*k;j<(index*k)+size[index];j++){
					if(tag[j].add.equals(tagadd)){
						tag[j].data=data1;
						System.out.println("HIT, new data: "+tag[j].data);
						tag[j].access+=1;
						c=1;
						break;
					}
				}
				if(c==0){
					if(size[index]<k){
						int mmemory_add=Integer.parseInt(add1.substring(0,16-B),2);
						tag[index*k+size[index]]=new Node(tagadd,data1,1);
						System.out.println("MISS, replacing with memory address and data: "+tag[index*k+size[index]].add+" "+tag[index*k+size[index]].data);
						size[index]+=1;

					}
					else{
						int mmemory_add=Integer.parseInt(add1.substring(0,16-B),2);
						int min=tag[index*k].access;
						int temp=index*k;
						for(int j=index*k+1;j<=(index*k)+(k-1);j++){
							if(tag[j].access<min){
								temp=j;
							}
						}
						tag[temp].add=tagadd;
						tag[temp].data=data1;
						tag[temp].access=1;
						System.out.println("MISS, replacing with memory adress and data: "+tag[temp].add+" "+tag[temp].data);
						size[index]+=0;//already full
					}
				}
			}
		}
	}
	
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		System.out.println("Enter Size of Cache (bytes), as the power of 2");
		int S=in.nextInt();//"Size of Cache (bytes), enter as the power of 2"
		System.out.println("Enter Size of Block (bytes), as the power of 2");
		int B=in.nextInt();//"Size of block (bytes), eneter as the power of 2"length of main memory array thrn becomes 2 ki power 16-B
		int Clines=(S-B);//as power of 2
		System.out.println("What cache implementation, 1 for direct mapping, 2 for fully associative, 3 for N-way set associative");
		int choice=in.nextInt();
		if(choice==1){
			dmap(Clines,B);
		}
		else if(choice==2){
			assoc(Clines,B);
		}
		else if(choice==3){
			int K=in.nextInt();//"K for K-way, enter as power of 2"
			k_way(Clines,B,K);
		}
		else{
			System.exit(0);
		}


	}
}