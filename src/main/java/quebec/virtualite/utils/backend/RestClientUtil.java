package quebec.virtualite.utils.backend;

public interface RestClientUtil
{
    <T> void delete(String url, String paramName,
                    Object paramValue);

    <T> T get(String url, String paramName,
              Object paramValue, Class<T> responseType);

    <T> T get(String url, Class<T> responseType);

    <T> T post(String url, Object entity, Class<T>
        responseType);

    void put(String url, Object entity);
}
