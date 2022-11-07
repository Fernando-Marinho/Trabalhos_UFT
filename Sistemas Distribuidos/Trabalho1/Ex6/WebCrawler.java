import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {

	private Queue<String> urlQueue;
	private static List<String> visitedURLs;
	private int controlThreads;
	
	public WebCrawler() {
		urlQueue = new LinkedList<>();
		visitedURLs = new ArrayList<>();
	}

	private static BufferedReader getFile(String address) throws IOException {
		File file=new File(address);
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
		return br;
	}

	private static class Search implements Runnable{
		private String s;
		private String rawHTML = "";
		int breakpoint;

		public Search(String s, int breakpoint) {
			this.s = s;
			this.breakpoint = breakpoint;
		}

		@Override
		public void run() {
			try {
				File foundedURLs1 = new File(System.getProperty("user.dir")+"\\urls.txt");
				//foundedURLs1.createNewFile();
				FileWriter foundedURLs = new FileWriter(foundedURLs1, true);

				File newFile = new File(System.getProperty("user.dir")+"\\encontradas.txt");
				if(!newFile.exists()) newFile.createNewFile();
				else {
					newFile.delete();
					newFile.createNewFile();
				}
				FileWriter writeFile = new FileWriter(newFile, true); 

				URL url = new URL(s);
				BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
				String inputLine = in.readLine();

				while(inputLine  != null){
					rawHTML += inputLine;
					inputLine = in.readLine();
				}
				in.close();

				BufferedReader br2=getFile(System.getProperty("user.dir")+"\\palavras.txt");
				String line2;  

				while((line2=br2.readLine())!=null)  {  
					int count=0;	
					for (int idx = 0; (idx = rawHTML.indexOf(line2, idx)) >= 0; idx++) { count++; }
					writeFile.write("["+url.toString()+"] "+line2+": "+count+"\n");
				}  
				

				String urlPattern = "(www|http:|https:)+[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
				Pattern pattern = Pattern.compile(urlPattern);
				Matcher matcher = pattern.matcher(rawHTML);

				getURLs(breakpoint, matcher);

				visitedURLs.forEach((v)->{
					try {
						foundedURLs.write("\n#["+url.toString()+"]"+v+"#");
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

				foundedURLs.close();
				writeFile.close();
				
				
			} catch (Exception e){
				e.printStackTrace();
			}

		}
	}

	public void crawl(int breakpoint, int max_threads) throws IOException, InterruptedException {

		BufferedReader br=getFile(System.getProperty("user.dir")+"\\urls.txt");
		String line;   
		this.setControlThreads(max_threads);

		while((line=br.readLine())!=null)  {  
			if(!line.startsWith("#") && !line.endsWith("#")) {
				urlQueue.add(line);
			};
		}  

		while(!urlQueue.isEmpty()){
			
			final Semaphore mySemaphore = new Semaphore(max_threads);
			try {
			    mySemaphore.acquire();
			    Search search = new Search(urlQueue.remove(), breakpoint);
				new Thread(search).start();
			} finally {
			    mySemaphore.release();
			}
			
			
		}
	}

	private static int getURLs(int breakpoint, Matcher matcher) {
		int urls = 0;
		while(matcher.find()){
			String actualURL = matcher.group();

			if(!visitedURLs.contains(actualURL)) {
				visitedURLs.add(actualURL);
				urls++;
			}

			if(urls >= breakpoint) break;
		}


		return urls;
	}
	

	public int getControlThreads() {
		return controlThreads;
	}

	public void setControlThreads(int controlThreads) {
		this.controlThreads = controlThreads;
	}

}