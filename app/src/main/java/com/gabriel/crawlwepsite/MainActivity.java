package com.gabriel.crawlwepsite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "Gabriel";
  String url = "http://afamily.vn/quiz.chn";
  WebDriver mWebDriver;
  String mPageSource;
  int mInitHeight;
  int mCurrentHeight = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    addControls();
    addEvents();
  }

  private void addControls() {
    mWebDriver = new ChromeDriver();
    mWebDriver.get(url);
    mPageSource = mWebDriver.getPageSource();
    Log.e(TAG, "addControls: " + mPageSource);
    By byTagName = new By.ByTagName("afwbl-ul");
    mInitHeight = mWebDriver.findElement(byTagName).getSize().getHeight();


    while (mInitHeight != mCurrentHeight) {
      mInitHeight = mWebDriver.findElement(byTagName).getSize().getHeight();

      //Scroll to bottom
      ((JavascriptExecutor) mWebDriver).executeScript("scroll(0," + mInitHeight + ");");

      System.out.println("Sleeping... wleepy");
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      mCurrentHeight = mWebDriver.findElement(byTagName).getSize().getHeight();
    }
  }

  private void addEvents() {

  }


}
