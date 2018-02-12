

Java Remote Method Invocation (RMI) teknolojisini kullanan basit bir komut satırı mesajlaşma uygulaması

Bu uygulama Java RMI kullanımını göstermek amacıyla yazılmıştır.
Uygulama ile ilgili blog postuna şu adresten ulaşabilirsiniz :
https://zihingezintisi.wordpress.com/2018/02/11/java-rmi-ile-chat-uygulamasi-gelistirme/


Notlar : 

1- Kodun basit ve sade olması için Client ve Server implementasyonları hiçbir senkronizasyon 
problemini göz önünde bulundurmamıştır.

2 - Kodun basit ve sade olması için hiçbir exception düzgün bir şekilde handle edilmemiştir.

3- Server uygulaması otomatik olarak localde 2020 portundan rmiregistry çalıştırmaktadır.
Bu sebeple 2 adet server uygulamasının aynı anda çalışması mümkün olmayacaktır.

4- Client uygulaması(ClientApp.java) komutsatırından kullanıcı adı alacak şekilde güncellebilir.
Bu güncelleme yapılmazsa, başka bir isim ile yeni bir client çalıştırmak istendiğinde 
ClientApp.java dosyasındaki username alanı güncellenmelidir.

5- Var olan sistemin üzerine basit bir şekilde GUI geliştirilebilir.
