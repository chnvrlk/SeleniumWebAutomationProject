                                                      Proje Hakkında

Merhabalar,                                                                
Proje bir altlık ve parametrik olduğu için herhangi bir web uygulamalarını test ederken kullanılabilecek bir projedir.

Java programlama dilinde kodlanmıştır. Log4j,Junit,gauge ve selenium araçları kullanılmıştır.

Gauge'daki spec ve conceptler sayesinde yazdığımız senaryoları projedeki tüm paydaşlar rahatlıkla okuyabilir ve test senaryosu yazabilirler.

Projede selenium'un 3 versiyonu kullanıldığı için drivers klasöründe chrome driver mevcuttur. test koşumu sırasında güncel kullanılan chrome versiyonunun driver dosyasını indirip projeye eklemeliyiz.

Projede test koşumu sonrasında loglama yapılmaktadır. her adımı consoleda görebilir ve test hata alırsa nerede kaldığını görebiliriz.

Projede BaseTest class'ı içerisinde before after driver'ı ayağa kaldırma ve kapatma gibi methodlar bulunuyor. Bunun yanısıra test koşumunu yaparkenki gereken driver ayarları (capabilities , chrome options) yapılıyor.

Projede test adımları parametrik yazılmıştır gauge kütüphanesi sayesinde BaseStep class'ı içerisinde @Step tagi ile yazılmıştır.
BaseStep class'ını inceleyip proje hakkında daha fazla bilgiye sahip olabilirsiniz.

-----------------------------------------------------------------
Hi,
Since the project is a base and parametric, it is a project that can be used when testing any web applications.

It is coded in the Java programming language. Log4j, Junit, gauge and selenium tools are used.

Thanks to the specs and concepts in Gauge, all stakeholders in the project can easily read and write test scenarios.

Since 3 versions of Selenium are used in the project, there is a chrome driver in the drivers folder. During the test run, we must download the driver file of the currently used chrome version and add it to the project.

Logging is done after the test run in the project. We can see each step in the console and if the test gets an error, we can see where it is.

Within the BaseTest class in the project, there are methods such as before after and turning off the driver. In addition to this, the necessary driver settings (capabilities, chrome options) are made while running the test.

In the project, the test steps were written parametrically. Thanks to the gauge library, they were written with the @Step tag in the BaseStep class.
You can have more information about the project by examining the BaseStep class.
