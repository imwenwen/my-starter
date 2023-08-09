package com.sxc;

import org.springframework.stereotype.Component;

@Component
public class ShortLinkGenerator {

//    private static final String BASE_URL = "https://uni-gate.shixiucai.com/";
    public static final LRUCacheUtil<String, String> shortLinkMap = new LRUCacheUtil<>(30);

    public static final LRUCacheUtil<String, String> LongLinkMap = new LRUCacheUtil<>(30);



    public String generateShortLink(String longLink) {
        boolean exists = LongLinkMap.exists(longLink);
        String shortLink = "";
        if (exists) {
             shortLink = LongLinkMap.getOne(longLink);
        }
        shortLink = ShortUrlUtil.generate(longLink);
        LongLinkMap.put(shortLink,longLink);
        if(!shortLinkMap.exists(shortLink)){
            shortLinkMap.put(shortLink,longLink);
        }
        return shortLink;
    }

    public String getLongLink(String shortLink) {
        boolean exists = shortLinkMap.exists(shortLink);
        if(exists){
            return shortLinkMap.getOne(shortLink);
        }
        return "";
    }


//    public String generateShortLink(String longLink) {
//        ShortUrl shortUrl = shortUrlDao.findByOrignUrl(longLink,Status.AVAILABLE.getCode());
//        if (shortUrl != null) {
//            String shortLink = shortUrl.getCurrent();
//            boolean exists = shortLinkMap.exists(shortLink);
//            if (exists) {
//                return shortLinkMap.getOne(longLink);
//            }
//            shortLinkMap.put(shortLink,longLink);
//        }
//        String shortLink = ShortUrlUtil.generate(longLink);
//        shortUrl = ShortUrl.builder().origin(longLink).current(shortLink).status(Status.AVAILABLE.getCode()).build();
//        shortUrlDao.save(shortUrl);
//        shortLinkMap.put(shortLink,longLink);
////        return BASE_URL + shortLink;
//        return shortLink;
//    }

//    public String getLongLink(String shortLink) {
////        if (shortLink.startsWith(BASE_URL)) {
////            String key = shortLink.substring(BASE_URL.length());
////            return shortLinkMap.getOrDefault(key, "");
////        }
//        boolean exists = shortLinkMap.exists(shortLink);
//        if(exists){
//            return shortLinkMap.getOne(shortLink);
//        }
//
//        return "";
//    }
}
