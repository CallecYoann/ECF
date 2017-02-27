/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regexEmail;

/**
 *
 * @author callec
 */
public class RegexEmail {
    
    public static void main(String[] args) {

        String regex = "^[_A-Za-z0-9-]{2,}+@[A-Za-z0-9-]{2,}+(\\.[A-Za-z]{2,})$"; //Expression reguliere
        String email = "abcd@efgh.ijkl"; //adresse mail a verifier
        Boolean bool = email.matches(regex);

        if (bool == false) {
            System.out.println("NON ! L\'adresse n\'est PAS valide");
        } else if (bool == true) {
            System.out.println("OUI ! L\'adresse est valide !");
        }

}
    
}
