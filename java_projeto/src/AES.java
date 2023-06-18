import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    
    private static String chaveSimetrica;
    private static String mensagem;
    private static SecretKey key; //essa chave ira formatar a chaveSimetrica para o funcionamento do aes
    private static byte[] msgEncriptada;
    private static byte[] msgDescriptada;

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("\n\ninforme uma chave: ");
        chaveSimetrica = sc.nextLine();
        key = new SecretKeySpec(chaveSimetrica.getBytes(), "AES");

        try{
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            System.out.print("Informe a mensagem que deseja encriptar: ");
            mensagem = sc.nextLine();
            msgEncriptada = cipher.doFinal(mensagem.getBytes());//neste momento é onde ocorre a criptografia de fato da mensagem
            System.out.println(new String("Mensagem Encriptada: " + msgEncriptada));

            cipher.init(Cipher.DECRYPT_MODE, key);

            msgDescriptada = cipher.doFinal(msgEncriptada);// neste momento ocorre a decifragem da mensagem criptografada
            String msgOriginal = new String(msgDescriptada);
            System.out.println("Mensagem descriptada: " + msgOriginal);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
