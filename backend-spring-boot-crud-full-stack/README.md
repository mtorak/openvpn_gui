# openvpn-install GUI için backend
## Kullanılan teknojiler
1. Spring Boot
2. Jsch http://www.jcraft.com/jsch/
3. openvpn-install https://github.com/angristan/openvpn-install

## Detaylar
- İlk olarak sdk'dan `Runtime.getRuntime().exec()` ve `ProcesBuilder` yaklaşımları denendi. Fakat process'lerin output'unu interaktif bir script için alma kısmı için net bir çözüm bulunamadı. Net'te bunu tam olarak çözen yok gibi. Sorun interaktif olması, çalışıp biten komut cıktıları için sorun yok.<br><br>

- Şimdi uygulanan yaklaşımda Jsch kullanarak lokal makinaya ssh ile bağlanıyor ve bu proje içinde bulunan __ovn.sh__ script'ini çağırıyor. __ovn.sh__ script'i __openvpn-install__ script'inin menülerini içeren bir script. Sadece bazı menüleri içeriyor, vpn kurulumu yapmıyor. Test edebilmek için input'ları gönderip sonuçları alabildigimizi test etmek yeterliydi. (Lokal makinada Open SSH Server kurulu olması gerkebilir.)<br><br>

- Şimdiki halinde `JschExecutor.java` test amaçlı olarak bir kerede tüm menü adımlarını çalıştırıp sonuçları alabiliyor. Ancak komutların hepsinin farklı rest endpoint'ler tarafından çalışıtırılması gerektigi için `OpenVpnExecutor.java` içinde farklı komutlar method'lara ayrıldı. Bu halinde jsch'nin session'ın yarattıgı önceki session'ların sonrakileri etkilemesi durumu var. Düzgün sonlandırılmamış session'ların başlattıgı bash shell'ler askıda kalıyor ve sonraki işlemleri etkiliyor. 