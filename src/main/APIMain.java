package main;
import java.time.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import com.google.gson.Gson;
public class APIMain {

	public static void main(String[] args) throws IOException,InterruptedException {
		var url="http://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=edff4d078a2542d4b93e189524f86d41";
		
		var request=HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
var client=HttpClient.newBuilder().build();
var response = client.send(request,HttpResponse.BodyHandlers.ofString());
//System.out.println(response.statusCode());
//System.out.println(response.body());
String jsonString = response.body().toString(); 
System.out.println(jsonString);
Gson gson=new Gson();
data d=gson.fromJson(jsonString, data.class);
//System.out.println(jsonString);
for(int i=0;i<d.articles.size();i++)
{
	news item=d.articles.get(i);
	System.out.println(item.src);
	System.out.println(item.author);
	System.out.println(item.title);
	System.out.println(item.description);
	System.out.println(item.url);

}
	}

}
//Main classes
class data
{
	String status;
	int totalResults;
	List<news> articles;
	
}
class news { 
	source src;
	String author;
	String title;
	String description;
	String url;
	String urlToImage;
	String publishedAt;
	String content;
}
 class source{
	String id;
	String name;
}
 //stats classes

class testing
{
	boolean success;
	set data;
}
class set
{
	String day;
	long totalSamplesTested;
	
}

class coviddata
{
	boolean success;
	datasum data;
	String lastRefreshed;
	String lastOriginUpdate;
	
}
class datasum
{
summ summary;
List<profile> regional;
}

class summ
{
	    long total;
		long confirmedCasesIndian ;
		long confirmedCasesForeign;
		long discharged;
		long deaths;
		long confirmedButLocationUnidentified;
}
class profile
{
	String loc;
	long confirmedCasesIndian;
	long confirmedCasesForeign;
	long discharged;
	long deaths;
	long totalConfirmed;
}
//hospital beds classes
class hosp
{
	Boolean success;
	hospdata data;
	
}
class hospdata
{
	hsum summary;
	List<hospprofile> regional;
	
}
class hsum
{
	long ruralHospitals;
	long ruralBeds;
	long urbanHospitals;
	long urbanBeds;
	long totalHospitals;
	long totalBeds;
}
class hospprofile
{
	String state;
	long ruralHospitals;
	long ruralBeds;
	long urbanHospitals;
	long urbanBeds;
	long totalHospitals;
	long totalBeds;
}
//covidhelpline
class covid
{
	Boolean success;
	covidd data;
}
class covidd
{
	covidcontacts contacts;
}
class covidcontacts
{
	covidprimary primary;
	List<helplineprofile> regional;

}
class covidprimary
{
	String number;
	String email;
	String twitter;
	String facebook;
	
}
class helplineprofile
{
	String loc;
	String number;
}
class notif
{
	Boolean success;
	notifd data;
}
class notifd
{
	List<notifs> notifications;
}
class notifs
{
	String title;
	String link;
}
//history classes value has been entered , can be improved with getting current date and inserting the table untill dates till today
class history
{
	boolean success;
	List<histobj> data;
}
class histobj
{
	String day;
	summary summary;
	List<regional> regional;
}
class summary
{
	int total;
	int confirmedCasesIndian;
	int confirmedCasesForeign;
	int discharged;
	int deaths;
	
}
class regional
{
	String loc;
	int confirmedCasesIndian;
	int confirmedCasesForeign;
	int discharged;
	int deaths;
	int totalConfirmed;
}