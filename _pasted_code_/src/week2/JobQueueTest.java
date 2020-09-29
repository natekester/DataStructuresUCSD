package week2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import week2.BuildHeap.Scan;

class JobQueueTest {

	@Test
	void test1() {
		
		long[] jobs ={1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		long[]jobs2 = jobs;
		

		int threads = 4;
		
		JobQueue job = new JobQueue();
		job.assignJobs(jobs, threads);
		JobQueue jobCheck = new JobQueue();
		jobCheck.assignJobsCheck(jobs2, threads);
				
		int[] assignedWorker = job.getAssignedWorker();
		long[] startTime = job.getstartTime();
		int[] assignedWorker2 = jobCheck.getAssignedWorker();
		long[] startTime2 = jobCheck.getstartTime();
		
		


		 for (int i = 0; i < assignedWorker.length; ++i) {
	            System.out.println("our code "+ assignedWorker[i] + " " + startTime[i]);
	            
	        }
		 
		 System.out.println("Now for the check ");
		 for (int i = 0; i <  assignedWorker.length; ++i) {
	            
			 
			 System.out.println("check worker "+ assignedWorker2[i] + " " + startTime2[i]);
	        }
		 for (int i = 0; i <  assignedWorker.length; ++i) {
	            
			 
			 assertEquals(startTime2[i], startTime[i]);
				assertEquals(assignedWorker[i], assignedWorker2[i]);
	        }

	

		

		
		
	}
	
	@Test
	void test2() {
		
		int size = 100;
		long[] jobs = CreateArray.createIntArr(size);
		
		
		int threads = size/10;
		
		JobQueue job = new JobQueue();
		job.assignJobs(jobs, threads);
		JobQueue jobCheck = new JobQueue();
		jobCheck.assignJobsCheck(jobs, threads);
				
		int[] assignedWorker = job.getAssignedWorker();
		long[] startTime = job.getstartTime();
		int[] assignedWorker2 = jobCheck.getAssignedWorker();
		long[] startTime2 = jobCheck.getstartTime();
		
		System.out.println(assignedWorker.length + " vs. check "+ assignedWorker2.length);
		
		
		 for (int i = 0; i <  assignedWorker.length; ++i) {
	            System.out.println(assignedWorker[i] + " " + startTime[i]);
	        }
		 
		 System.out.println("Now for the check ");
		 for (int i = 0; i <  assignedWorker.length; ++i) {
	            
			 
			 System.out.println(assignedWorker2[i] + " " + startTime2[i]);
	        }
		 
		 for (int i = 0; i <  assignedWorker.length; ++i) {
	            
			 
			 assertEquals(startTime2[i], startTime[i]);
				assertEquals(assignedWorker[i], assignedWorker2[i]);
	        }
		
	}
	

	@Test
	void Challenge2() {
		
	
		long[] jobs = Scan.scanj("124860658 388437511 753484620 349021732 311346104 235543106 665655446 28787989 706718118 409836312 217716719 757274700 609723717 880970735 972393187 246159983 318988174 209495228 854708169 945600937 773832664 587887000 531713892 734781348 603087775 148283412 195634719 968633747 697254794 304163856 554172907 197744495 261204530 641309055 773073192 463418708 59676768 16042361 210106931 901997880 220470855 647104348 163515452 27308711 836338869 505101921 397086591 126041010 704685424 48832532 944295743 840261083 407178084 723373230 242749954 62738878 445028313 734727516 370425459 607137327 541789278 281002380 548695538 651178045 638430458 981678371 648753077 417312222 446493640 201544143 293197772 298610124 31821879 46071794 509690783 183827382 867731980 524516363 376504571 748818121 36366377 404131214 128632009 535716196 470711551 19833703 516847878 422344417 453049973 58419678 175133498 967886806 49897195 188342011 272087192 798530288 210486166 836411405 909200386 561566778" );
		
		int threads = 10;
		
		
		
		
		JobQueue job = new JobQueue();
		job.assignJobs(jobs, threads);
		JobQueue jobCheck = new JobQueue();
		jobCheck.assignJobsCheck(jobs, threads);
				
		int[] assignedWorker = job.getAssignedWorker();
		long[] startTime = job.getstartTime();
		int[] assignedWorker2 = jobCheck.getAssignedWorker();
		long[] startTime2 = jobCheck.getstartTime();
		

		 for (int i = 0; i < assignedWorker.length; ++i) {
			 
			 if( assignedWorker[i] != assignedWorker2[i] || startTime[i] != startTime2[i]) {
				 
			 
	            System.out.println("Ours: "+assignedWorker[i] + " " + startTime[i]);
	            System.out.println("check: "+ assignedWorker2[i] + " " + startTime2[i]);
			 	}
		 	}
		 

		        for (int i = 0; i < jobs.length; ++i) {
		            System.out.println(assignedWorker[i] + " " + startTime[i]);
		        }

		 

		 for (int i = 0; i <  assignedWorker.length; ++i) {
	            
			 
			 assertEquals(startTime2[i], startTime[i]);
				assertEquals(assignedWorker[i], assignedWorker2[i]);
	        }
	
		
		
	}
	

	@Test
	void stringTest() {
		


		
	}
	


}
