package keywordT;

import java.util.Random;

public class MatrixMul {
	public static void main(String[] args) {
		int size = 2048;
		float A[][]= new float[size][size];
		float B[][]= new float[size][size];
		float C[][] = new float[size][size];
		float D[][] = new float[size][size];
		Random rand = new Random();
		long start = System.currentTimeMillis();
		for(int i = 0; i<size ; i++ ) {
			for(int j=0; j<size ; j++) {
				A[i][j] = rand.nextFloat();
				B[i][j] = rand.nextFloat();
				C[i][j] = 0;
			}
		}
		System.out.println("A matrix is :");
		for(int i=0;i<size;i++) {
			for(int j = 0 ;j<size ; j++) {
//				System.out.print(A[i][j]+"   ");
			}
//			System.out.println();
		}
		
		System.out.println("B matrix is :");
		for(int i=0;i<size;i++) {
			for(int j = 0 ;j<size ; j++) {
//				System.out.print(B[i][j]+"   ");
			}
//			System.out.println();
		}
		System.out.println("final matrix C is :");
		for (int k = 0; k<size ; k++) {
			for(int i=0; i<size ; i++) {
				float r = A[i][k];
				for(int j=0; j<size;j++) {
					C[i][j] += (r * B[k][j]);
				}
			}
		}
		long end1 = System.currentTimeMillis();
		for(int i= 0 ; i<size ; i++) {
			for(int j= 0;j<size ;j ++) {
//				System.out.print(C[i][j] + "   ");
			}
//			System.out.println();
		}
		
		System.out.println("Test :");
		
		System.out.println("Method 2 with using blocking");
		int  block = 8;
		int  nr = size/block;
		float[][] Cc = new float[nr][nr];
		float[][] Ac = new float[nr][nr];
		float[][] Bc = new float[nr][nr];
		for(int jc = 0 ; jc < size ; jc += nr) {
			for (int kc = 0; kc < size ; kc +=  nr) {
				for (int k = 0 ; k<nr;k++) {
					for (int j = 0 ; j < nr ; j++) {
						Bc[k][j] = B[k+kc][j+jc];
					}
				}
				for (int ic = 0 ; ic < size ; ic += nr) {
					for(int i = 0; i < nr ; i++) {
						for(int k = 0 ; k < nr ; k++) {
							Ac[i][k] = A[i+ic][k+kc];
						}
						for(int j = 0; j<nr ; j++) {
							Cc[i][j] = 0;
						}
					}
						for(int ir = 0; ir < nr;ir++) {
							for(int kr = 0 ; kr<nr;kr++) {
								float ac = Ac[ir][kr];
								for(int jr =0;jr < nr ; jr++) {
									Cc[ir][jr] += ac*Bc[kr][jr];
								}
							}
						}
						for(int i = 0 ; i < nr ; i++) {
							for (int j =0 ; j < nr ; j++) {
								D[i+ic][j+jc] += Cc[i][j];
							}
						}
					
				}
			}
		}
		long end2 = System.currentTimeMillis();
		System.out.println("time duration 1 cost : " + (end1 - start));
		System.out.println("time duration 2 cost : " + (end2 - end1));
		float error = 0;
		for(int i= 0 ; i<size ; i++) {
			for(int j= 0;j<size ;j ++) {
				error += Math.abs(D[i][j] - C[i][j]);
			}
			
		}
		System.out.println(error);
	}
}
