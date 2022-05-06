package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Search;
import com.model2.mvc.service.domain.User;



public class RestHttpClientApp {
	
	// main Method
	public static void main(String[] args) throws Exception{
		
		////////////////////////////////////////////////////////////////////////////////////////////
		// �ּ��� �ϳ��� ó���ذ��� �ǽ�
		////////////////////////////////////////////////////////////////////////////////////////////
		
//		System.out.println("\n====================================\n");
//		// 1.1 Http Get ��� Request : JsonSimple lib ���
//		RestHttpClientApp.getUserTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get ��� Request : CodeHaus lib ���
//		RestHttpClientApp.getUserTest_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Post ��� Request : JsonSimple lib ���
//		RestHttpClientApp.LoginTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Post ��� Request : CodeHaus lib ���
//		RestHttpClientApp.LoginTest_Codehaus();		
		
		//System.out.println("\n====================================\n");
		// Http Get ��� Request : CodeHaus lib ���
		//RestHttpClientApp.addUserTest_Codehaus();
		
		//System.out.println("\n====================================\n");
		// Http Get ��� Request : CodeHaus lib ���
		//RestHttpClientApp.updateUserTest_Codehaus();
		
		//System.out.println("\n====================================\n");
		// Http Get ��� Request : CodeHaus lib ���
		//RestHttpClientApp.listUserTest_Codehaus();
		
		//400 null ��� �ȵ�
		//System.out.println("\n====================================\n");
		// Http Get ��� Request : CodeHaus lib ���
		//RestHttpClientApp.checkDuplicationTest_Codehaus();
		
		
		
		//System.out.println("\n====================================\n");
		// Http Get ��� Request : CodeHaus lib ���
		//RestHttpClientApp.getProductTest_Codehaus();
		
		// HttpGet ��� Request : CodeHaus lib ���
		//RestHttpClientApp.addProductTest_Codehaus();
		
		//HttpPost ��� Request : CodeHaus lib ���
		//RestHttpClientApp.updateProductTest_Codehaus();
		
		RestHttpClientApp.listProductTest_CodeHaus();
	}
	
	
//================================================================//
	//1.1 Http Protocol GET Request : JsonSimple 3rd party lib ���
	public static void getUserTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/user/json/getUser/admin";
				
		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib ���
	public static void getUserTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/user/json/getUser/admin";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}
//================================================================//	
	
//================================================================//
	//2.1 Http Protocol POST Request : FromData ���� / JsonSimple 3rd party lib ���
	public static void LoginTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ ��� 1 : String ���]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
		//[ ��� 2 : JSONObject ���]
		JSONObject json = new JSONObject();
		json.put("userId", "admin");
		json.put("password", "1234");
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	
	}
	
	
	//2.2 Http Protocol POST ��� Request : FromData���� 
	//==> JsonSimple + codehaus 3rd party lib ���
	public static void LoginTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
//		//[ ��� 1 : String ���]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
//		//[ ��� 2 : JSONObject ���]
//		JSONObject json = new JSONObject();
//		json.put("userId", "admin");
//		json.put("password", "1234");
//		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		//[ ��� 3 : codehaus ���]
		User user01 =  new User();
		user01.setUserId("admin");
		user01.setPassword("1234");
		ObjectMapper objectMapper01 = new ObjectMapper();
		//Object ==> JSON Value �� ��ȯ
		String jsonValue = objectMapper01.writeValueAsString(user01);
		
		System.out.println(jsonValue);
		
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
		
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}	
	
	public static void addUserTest_Codehaus() throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/addUser";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept","application/json");
		httpPost.setHeader("Content-Type","application/json");
		
		User user01 = new User();
		user01.setUserId("user24");
		user01.setUserName("SCOTT");
		user01.setPassword("2424");
		user01.setSsn("99091010101");
		user01.setAddr("����");
		user01.setPhone("010-1111-2222");
		user01.setEmail("green@naver.com");	
		
		ObjectMapper objectMapper01 = new ObjectMapper();
		String jsonValue = objectMapper01.writeValueAsString(user01);
		
		System.out.println("jsonValue : "+jsonValue);
		
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"UTF-8");
		
		httpPost.setEntity(httpEntity01);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity responseHttpEntity = httpResponse.getEntity();
		
		InputStream is = responseHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println("jsonobj"+jsonobj);
		
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(jsonobj.toString(), User.class);
		System.out.println("user"+user);
		
		
	}
	
	public static void updateUserTest_Codehaus() throws Exception {
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/updateUser";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept","application/json");
		httpPost.setHeader("Content-Type","application/json");
		
//		JSONObject json = new JSONObject();
//		json.put("userId","user24");
//		json.put("userName", "��ı");
//		json.put("ssn","���");
//		json.put("phone", "010-9119-1991");
//		json.put("email", "blue@naver.com");
//		
//		System.out.println("json"+json);
		
		User user01 = new User();
		user01.setUserId("user24");
		user01.setUserName("��ı");
		user01.setPassword("2424");
		user01.setSsn("9999991010101");
		user01.setAddr("���");
		user01.setPhone("010-9119-1991");
		user01.setEmail("blue@naver.com");
		
		System.out.println("user01"+user01);
		
		ObjectMapper objectMapper01 = new ObjectMapper();
		String jsonValue = objectMapper01.writeValueAsString(user01);
		
		HttpEntity requestHttpEntity = new StringEntity(jsonValue, "utf-8");
		httpPost.setEntity(requestHttpEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		System.out.println(httpResponse);
		System.out.println();
		
		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//Stream��ü ���� ����
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println("jsonobj : "+jsonobj);
		
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(jsonobj.toString(), User.class);
		System.out.println("user : "+user);
		
	}
	
	public static void listUserTest_Codehaus() throws Exception {
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/listUser";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept","application/json");
		httpPost.setHeader("Content-Type","application/json");		
		
		Search search = new Search();
		ObjectMapper objectMapper01 = new ObjectMapper();
		String jsonValue = objectMapper01.writeValueAsString(search);
		
		HttpEntity requestHttpEntity = new StringEntity(jsonValue.toString(), "UTF-8");
		httpPost.setEntity(requestHttpEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		System.out.println("httpResponse"+httpResponse);
		
		HttpEntity responseHttpEntity = httpResponse.getEntity();
		
		InputStream is = responseHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println("jsonobj : "+jsonobj);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Map returnUserList = objectMapper.readValue(jsonobj.toString(), Map.class);
		System.out.println("returnUser : "+returnUserList);
		
	}
	
	public static void checkDuplicationTest_Codehaus() throws Exception {
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/checkDuplication";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept","application/json");
		httpPost.setHeader("Content-Type","application/json");
		
		User user01 = new User();
		user01.setUserId("user50");
		
		ObjectMapper objectMapper01 = new ObjectMapper();
		String jsonValue = objectMapper01.writeValueAsString(user01);
		
		HttpEntity requestHttpEntity = new StringEntity(jsonValue.toString(),"UTF-8");
		httpPost.setEntity(requestHttpEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		System.out.println("httpResponse"+httpResponse);
		
		HttpEntity responseHttpEntity = httpResponse.getEntity();
		
		InputStream is = responseHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println("jsonobj"+jsonobj);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Map map = objectMapper.readValue(jsonobj.toString(), Map.class);
		System.out.println("map"+map);
		
	}
	
	public static void getProductTest_Codehaus() throws Exception {
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/product/json/getProduct/10000";
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept","application/json");
		httpGet.setHeader("Content-Type","application/json");
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		System.out.println(httpResponse);
		
		HttpEntity httpEntity = httpResponse.getEntity();
		
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println("jsonobj : "+jsonobj);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		System.out.println("product"+product);
	}
	
	public static void addProductTest_Codehaus() throws Exception {
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/addProduct";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept","application/json");
		httpPost.setHeader("Content-Type","application/json");
		
		Product product = new Product();
		product.setProdName("�߰�����");
		product.setProdDetail("���");
		product.setManuDate("20220426");
		product.setPrice(33333);
		product.setFileName("�ձ۵ձ�");
		
		ObjectMapper objectMapper01 = new ObjectMapper();
		String jsonValue = objectMapper01.writeValueAsString(product);
		
		HttpEntity requestHttpEntity = new StringEntity(jsonValue,"UTF-8");
		httpPost.setEntity(requestHttpEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		System.out.println("httpResponse : "+httpResponse);
		
		HttpEntity httpEntity = httpResponse.getEntity();
		
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println("jsonobj : "+jsonobj);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Product result = objectMapper.readValue(jsonobj.toString(), Product.class);
		System.out.println("result"+result);
	}
	
	public static void updateProductTest_Codehaus() throws Exception {
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/updateProduct";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept","application/json");
		httpPost.setHeader("Content-Type","application/json");
		
		Product product = new Product();
		product.setProdNo(10020);
		product.setProdName("�߰�����");
		product.setProdDetail("���");
		product.setManuDate("20220427");
		product.setPrice(44444);
		product.setFileName("���ܾ�");
		
		ObjectMapper objectMapper01 = new ObjectMapper();
		String jsonValue = objectMapper01.writeValueAsString(product);
		
		HttpEntity requestHttpEntity = new StringEntity(jsonValue,"UTF-8");
		httpPost.setEntity(requestHttpEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		System.out.println("httpResponse"+httpResponse);
		
		HttpEntity responseHttpEntity = httpResponse.getEntity();
		
		InputStream is = responseHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[Server���� ���� Data Ȯ��]");
		String serverData = br.readLine();
		System.out.println("serverData"+serverData);
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Product result = objectMapper.readValue(jsonobj.toString(), Product.class);
		System.out.println("result"+result);
	
	}
	
	public static void listProductTest_CodeHaus() throws Exception {
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/listProduct";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept","application/json");
		httpPost.setHeader("Content-Type","application/json");
		
		Search search = new Search();
		ObjectMapper objectMapper01 = new ObjectMapper();
		String jsonValue = objectMapper01.writeValueAsString(search);
		
		HttpEntity requestHttpEntity = new StringEntity(jsonValue.toString(), "UTF-8");
		httpPost.setEntity(requestHttpEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		System.out.println("httpResponse"+httpResponse);
		
		HttpEntity responseHttpEntity = httpResponse.getEntity();
		
		InputStream is = responseHttpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[Server���� ���� Data Ȯ��]");
		String serverData = br.readLine();
		System.out.println("serverData"+serverData);
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Map result = objectMapper.readValue(jsonobj.toString(), Map.class);
		System.out.println("result"+result);
		
		
	}
	
	
}