import java.io.*;

public class Tennis {
	static int pntA=0, pntB=0, setA=0, setB=0, gameA=0, gameB=0;
	static final int points[]={0, 15, 30, 40};
	
	public static void printScores(int flag){
		System.out.println("player: A\t\tB");
		System.out.println("sets:   "+setA+"\t\t"+setB);
		System.out.println("games:  "+gameA+"\t\t"+gameB);
		if(flag==-1){
			System.out.println("points: "+points[pntA]+"\t\t"+points[pntB]);
		}
		else if(flag==0){
			System.out.println("points: "+"adv"+"\t\t"+points[pntB]);
		}
		else{
			System.out.println("points: "+points[pntA]+"\t\t"+"adv");
		}
		
	}
	
	public static int checkState(int pntA, int pntB){
		int result=0;
		if(pntA!=3 && pntB!=3){
			result=0;
		}
		else if(pntA==3 && pntB==3){
			result=1;
		}
		return result;
	}
	
	public static void main(String[] args){
		String input="ABABABABAB";
		
		int deuce=0;
		
		int idx=0;
		int flag=-1;
		while(idx!=input.length()){
			if(deuce==0){
				if(idx<input.length()){
					if(input.charAt(idx)=='A'){
						pntA++;
						idx++;
						deuce=checkState(pntA, pntB);
					}
					else{
						pntB++;
						idx++;
						deuce=checkState(pntA, pntB);
					}
				}
				else{
					break;
				}
			}
			else if(deuce==1){
				//Lookahead
				if(idx<input.length() && idx+1<input.length()){
					//AA or BB
					if(input.charAt(idx)==input.charAt(idx+1)){
						if(input.charAt(idx)=='A'){
							pntA=0;
							pntB=0;
							gameA++;
							if(gameA>=6 && gameA-gameB>=2){
								setA++;
								gameA=0;
								gameB=0;
							}
						}
						else{
							pntA=0;
							pntB=0;
							gameB++;
							if(gameB>=6 && gameB-gameA>=2){
								setB++;
								gameA=0;
								gameB=0;
							}
						}
						idx+=2;
						deuce=0;
					}
					//AB or BA
					else{
						deuce=1;
						idx+=2;
					}
				}
				//Advantage
				else if(idx<input.length()){
					if(input.charAt(idx)=='A'){
						//Advantage A
						flag=0;
					}
					else{
						//Advantage B
						flag=1;
					}
					idx++;
				}
			}	
		}
		printScores(flag);	
	}
}
