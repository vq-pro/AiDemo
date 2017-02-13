package quebec.virtualite.utils.backend.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import quebec.virtualite.utils.backend.RestClientUtil;

import java.util.HashMap;
import java.util.Map;

@Service
public class RestClientUtilImpl implements RestClientUtil
{
    private String serverUrl;

    @Autowired
    private Environment environment;

    @Override
    public <T> void delete(final String url, final String paramName,
                           final Object paramValue)
    {
        checkInit();

        Map<String, Object> params = new HashMap<>();
        params.put(paramName, paramValue);

        ResponseEntity<Void> response = getTemplate()
            .exchange(serverUrl + url + "?" + paramName + "={" + paramName + "}",
                HttpMethod.DELETE, null, Void.class, params);
    }

    @Override
    public <T> T get(final String url, final String paramName,
                     final Object paramValue, Class<T> responseType)
    {
        checkInit();

        Map<String, Object> params = new HashMap<>();
        params.put(paramName, paramValue);

        ResponseEntity<T> response = getTemplate()
            .exchange(serverUrl + url + "?" + paramName + "={" + paramName + "}",
                HttpMethod.GET, null, responseType, params);

        return response.getBody();
    }

    @Override
    public <T> T get(final String url, Class<T> responseType)
    {
        //        HttpAuthentication httpAuthentication = new
        // HttpBasicAuthentication("username", "password");
        //        HttpHeaders requestHeaders = new HttpHeaders();
        //        requestHeaders.setAuthorization(httpAuthentication);
        //
        //        HttpEntity<?> httpEntity = new HttpEntity<Object>
        // (requestHeaders);

        //        String plainCreds = SecurityConfig.USERNAME + ":" +
        // SecurityConfig.PASSWORD;
        //        byte[] plainCredsBytes = plainCreds.getBytes();
        //        byte[] base64CredsBytes = Base64.encodeBase64
        // (plainCredsBytes);
        //        String base64Creds = new String(base64CredsBytes);
        //
        //        HttpHeaders headers = new HttpHeaders();
        //        headers.add("Authorization", "Basic " + base64Creds);
        //
        //        HttpEntity<String> httpEntity = new HttpEntity<String>
        // (headers);

        //        MultiValueMap<String, String> headers = new
        // LinkedMultiValueMap<String, String>();
        //        headers.add("HeaderName", "value");
        //        headers.add("Content-Type", "application/json");
        //
        //        HttpEntity httpEntity = new HttpEntity(headers);

        //        final ResponseEntity<T> response = getTemplate()
        //            .exchange(serverUrl + url, HttpMethod.GET, getToken(),
        // responseType);

        checkInit();

        final ResponseEntity<T> response = getTemplate()
            //            .exchange(serverUrl + url, HttpMethod.GET,
            // httpEntity, responseType);
            .exchange(serverUrl + url, HttpMethod.GET, null, responseType);

        return response.getBody();
    }

    @Override
    public <T> T post(final String url, final Object entity, Class<T>
        responseType)
    {
        checkInit();

        ResponseEntity<T> response = getTemplate()
            .exchange(serverUrl + url, HttpMethod.POST, new HttpEntity<>(entity), responseType);

        return response.getBody();
    }

    @Override
    public void put(final String url, Object entity)
    {
        final ResponseEntity<Void> response = getTemplate()
            .exchange(serverUrl + url, HttpMethod.PUT, new HttpEntity<>(entity), Void.class);
    }

    private void checkInit()
    {
        if (serverUrl == null)
        {
            this.serverUrl =

                environment.getProperty("hostname")
                + ":"
                + environment.getProperty("local.server.port");
        }
    }

    private RestTemplate getTemplate()
    {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
            .add(new MappingJackson2HttpMessageConverter());

        return restTemplate;
    }

    //    private HttpEntity<String> getToken()
    //    {
    // TODO Re-enable REST security
    //        String plainCreds = SecurityConfig.USERNAME + ":" +
    // SecurityConfig
    //            .PASSWORD;
    //        byte[] plainCredsBytes = plainCreds.getBytes();
    //        byte[] base64CredsBytes = Base64.encodeBase64
    // (plainCredsBytes);
    //        String base64Creds = new String(base64CredsBytes);
    //
    //        HttpHeaders headers = new HttpHeaders();
    //        headers.add("Authorization", "Basic " + base64Creds);
    //
    //        return new HttpEntity<String>(headers);

    //        return null;
    //    }
}
