

Java Remote Method Invocation (RMI) teknolojisini kullanan basit bir komutsatırı mesajlaşma uygulaması

Bu uygulama Java RMI kullanımını göstermek amacıyla yazılmıştır.

Norlar : 

1- Kodun basit ve sade olması için Client ve Server implementasyonları hiçbir senkronizasyon 
problemini göz önünde bulundurmamıştır.

2 - Kodun basit ve sade olması için hiçbir exception düzgün bir şekilde handle edilmemiştir.

3- Server uygulaması otomatik olarak localde 2020 portundan rmiregistry çalıştırmaktadır.
Bu sebeple 2 adet server uygulamasının aynı anda çalışması mümkün olmayacaktır.

4- Client uygulaması(ClientApp.java) komutsatırından kullanıcı adı alacak şekilde güncellebilir.
Bu güncelleme yapılmazsa, başka bir isim ile yeni bir client çalıştırmak istendiğinde 
ClientApp.java dosyasındaki username alanı güncellenmelidir.



