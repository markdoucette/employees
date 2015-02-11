package ca.bcit.a00057006.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mdoucette on 15-02-06.
 */
public class URLUtilities {
    public URLUtilities() {
    }

    public static String getSafeURLString(String url, HttpServletRequest request, HttpServletResponse response) {

        return response.encodeURL(request.getContextPath() + "/" + url);
    }
}
