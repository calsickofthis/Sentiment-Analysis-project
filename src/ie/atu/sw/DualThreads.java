/**
 * 
 */
package ie.atu.sw;

import static java.lang.System.out;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.StructuredTaskScope;
import java.util.HashMap;

/**
 * @author Caolan Maguire
 * @apiNote  is the DualThreads for running the two threads
 */
public class DualThreads {
	
	/**
	 * @apiNote  for the method
	 */
//	public DualThreads(String LexiconPath, String OutputPath) {
//		
//	}
	
	private Set<String> words = new ConcurrentSkipListSet<>();
	
	HashMap<Integer, String> TweetsMap = new HashMap<Integer, String>();
	HashMap<String, String> Lexicon = new HashMap<String, String>();
	double sum = 0.0;
	String ChosenTweet;
	
	public String getChosenTweet() {
	    return ChosenTweet;
	}

	public void setChosenTweet(String chosenTweet) {
	    ChosenTweet = chosenTweet;
	}

	public double getSum() {
	    return sum;
	}

	public void setSum(double sum) {
	    this.sum = sum;
	}

	public void LexiconReader(String LexiconPath) throws Throwable {
	    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
		Files.lines(Paths.get(LexiconPath)).forEach(text -> {
			scope.fork(() -> {
			    	String[] b = text.split(",");
			    	
			    	// place lexicon into Hashmap with word as key
			    	Lexicon.put(b[0], b[1]);

				process(text);
				return null;
			});
		});
		scope.join();
		scope.throwIfFailed(e -> e);  
		
//		out.println(words);
//		out.println(words.size());
	    }
	}
	
	public void process(String text) {
		Arrays.stream(text.split("\\s+")).forEach(w -> words.add(w));
	}
	
	int i = 0;
	
	public void TweetsReader(String MenuPath) throws Throwable {
	    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
		Files.lines(Paths.get(MenuPath)).forEach(text -> {
		    	scope.fork(() -> {
		    	TweetsMap.put(TweetsMap.size(), text);
				
			    	return null;
			});
		});
		scope.join();
		scope.throwIfFailed(e -> e);  
		
//		out.println(words);
//		out.println(words.size());
	    }
	}
	
	public double CalculateSentiment(int URL) {
	    // Chosen Tweet
	    
	    double sum = 0;
	    
	    String[] myData = TweetsMap.get(URL).split(" ");
	    for (String s: myData) {
	        if(Lexicon.get(s) != null) {
	            sum = sum + Double.parseDouble(Lexicon.get(s));
	        }
	        
	    }
	    
	    sum = sum / myData.length;
	    
	    setChosenTweet(TweetsMap.get(URL));
	    setSum(sum / myData.length);
	    
	    System.out.println("\n\n Sentiment Analysis : ");
	    System.out.println(sum / myData.length);
	    
	    return i;
	}
	
	/**
	 * @author Caolan Maguire
	 * @apiNote is my go method for running two threads
	 */
	public void go(String LexiconPath, String MenuPath, int URL) throws Exception {
		
		// read the lexicon file
		var t1 = Thread.ofVirtual()
			.unstarted(()-> {
			    // call the lexicon reader
			    try {
				LexiconReader(LexiconPath);
			    } catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }	    		
			});
		
		t1.start();
		t1.join();
//		System.out.println(Lexicon);
		
		var t2 = Thread.ofVirtual()
			       .unstarted(()-> {
			    	   try {
				    TweetsReader(MenuPath);
				} catch (Throwable e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}
			       });
		t2.start();
		t2.join();	
//		System.out.println(TweetsMap);
		
 	   	
 	   	
 	   	var task = ForkJoinPool.commonPool() //Not the same FJP used by virtual threads
 	   			               .submit(()-> CalculateSentiment(URL));
 	   	task.join();
 	   
 	   	
 	   	System.out.println("Cores: " + Runtime.getRuntime().availableProcessors());	   	
 	   	System.out.println("Pools: " + Runtime.getRuntime().availableProcessors());
 	   	System.out.println("Platform Threads: " + Thread.activeCount());
 	   	
	}
}
