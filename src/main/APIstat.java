package main;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import com.google.gson.Gson;
public class APIstat {
	public static void main(String[] args) throws IOException,InterruptedException{
		var url="https://api.rootnet.in/covid19-in/stats/latest";
		
		var request=HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
var client=HttpClient.newBuilder().build();
var response = client.send(request,HttpResponse.BodyHandlers.ofString());
//System.out.println(response.statusCode());
//System.out.println(response.body());
String jsonString = response.body().toString(); 
//System.out.println(jsonString);
Gson gson=new Gson();
coviddata d=new coviddata();
d=gson.fromJson(jsonString, coviddata.class);

	}
}
