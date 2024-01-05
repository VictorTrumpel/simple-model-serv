package ru.simplemodel.app.yAPI;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class YFileApi {
  @Value("${app.y_api_url}")
  private String baseUrl;

  @Value("${app.ytoken}")
  private String yToken;

  private String fileApiUrl = "/disk/resources";

  public JsonNode getDirInfo(String dirName) throws Exception {
    String fields = "_embedded.items.size,_embedded.items.name,_embedded.items.file";

    String resUrl = getApiUrl() + "?path=" + dirName + "&fields=" + fields;

    ResponseEntity<JsonNode> response = getFetch(resUrl);

    if (response.getStatusCode().is2xxSuccessful()) {
      JsonNode responseData = response.getBody().get("_embedded").get("items");

      return responseData;
    }

    throw new Exception("" + response.getStatusCodeValue());
  }
  
  public void createDirectory(String dirName) throws Exception {
    String resUrl = getApiUrl() + "?path=" + dirName;

    ResponseEntity<JsonNode> response = putFetch(resUrl, "");

    if (response.getStatusCode().is2xxSuccessful()) {
      return;
    }

    throw new Exception("" + response.getStatusCodeValue());
  }

  public void deleteDirectory(String dirName) throws Exception {
    String resUrl = getApiUrl() + "?path=" + dirName;
    
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = getHeaders();

    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<JsonNode> response = restTemplate.exchange(
      resUrl,
      HttpMethod.DELETE, 
      request, 
      JsonNode.class
    );
    
    if (response.getStatusCode().is2xxSuccessful()) {
      return;
    }

    throw new Exception("" + response.getStatusCodeValue());
  }

  public void uploadFile(MultipartFile file, String filePath) throws Exception {
    String uploadFileUrl = this.getUploadUrl(filePath);

    ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes());

    ResponseEntity<JsonNode> response = putFetch(uploadFileUrl, fileAsResource);

    if (response.getStatusCode().is2xxSuccessful()) {
      return;
    }

    throw new Exception("" + response.getStatusCodeValue());
  }

  private String getUploadUrl(String filePath) throws Exception {
    String resUrl = getApiUrl() + "/upload?path=" + filePath;

    ResponseEntity<JsonNode> response = getFetch(resUrl);

    if (response.getStatusCode().is2xxSuccessful()) {
      JsonNode responseData = response.getBody();
      return responseData.get("href").toString().replaceAll("^\"|\"$", "");
    }

    throw new Exception("" + response.getStatusCodeValue());
  }

  private ResponseEntity<JsonNode> getFetch(String getUrl) {
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = getHeaders();

    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<JsonNode> response = restTemplate.exchange(
      getUrl,
      HttpMethod.GET, 
      request, 
      JsonNode.class
    );

    return response;
  }

  private <Entity> ResponseEntity<JsonNode> putFetch(String putUrl, Entity body) {
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = getHeaders();

    HttpEntity<Entity> request = new HttpEntity<>(body, headers);

    return restTemplate.exchange(
      putUrl,
      HttpMethod.PUT,
      request,
      JsonNode.class
    );
  }

  private HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "OAuth " + yToken);
    return headers;
  }

  private String getApiUrl() {
    return baseUrl + fileApiUrl;
  }
}
