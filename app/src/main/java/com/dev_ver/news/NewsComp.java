package com.dev_ver.news;

import androidx.annotation.NonNull;

public class NewsComp {
    static public class NewsComponent
    {
      String title;
      String author;
      String url;
      String imageUrl;
      String description;


        public NewsComponent(@NonNull String... strings)
      {
          title=strings[0];
          author=strings[1];
          url=strings[2];
          imageUrl=strings[3];
          description=strings[4];
      }
    }

}
