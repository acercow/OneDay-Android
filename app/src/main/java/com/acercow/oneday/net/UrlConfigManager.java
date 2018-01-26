package com.acercow.oneday.net;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.acercow.oneday.R;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaosen on 2018/1/24.
 */

public class UrlConfigManager {
    public static List<UrlData> sUrlDatalist = new ArrayList<>();
    public static void fetchUrlDataFromXml(Context context) {
        if (!sUrlDatalist.isEmpty()) {
            throw new IllegalStateException("Class variable: sUrlDatalist must be empty while calling fetchUrlDataFromXml()");
        }
        XmlResourceParser xrp = context.getResources().getXml(R.xml.url);
        try {
            while (xrp != null && xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {

                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    String tagName = xrp.getName();
                    if ("Node".equals(tagName)) {
                        UrlData urlData = new UrlData();
                        urlData.setKey(xrp.getAttributeValue(null, "Key"));
                        urlData.setUrl(xrp.getAttributeValue(null, "Url"));
                        urlData.setExpires(Long.valueOf(xrp.getAttributeValue(null, "Expires")));
                        urlData.setNetType(xrp.getAttributeValue(null, "NetType"));
                        urlData.setMockClass(xrp.getAttributeValue(null, "MockClass"));
                        sUrlDatalist.add(urlData);
                    }
                }
                xrp.next();

            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UrlData findUrlData(Context context, String requestKey) {
        if (sUrlDatalist == null || sUrlDatalist.isEmpty()) {
            fetchUrlDataFromXml(context);
        }
        for (UrlData urlData : sUrlDatalist) {
            if (requestKey.equals(urlData.getKey())) {
                return urlData;
            }
        }
        throw null;
    }
}
