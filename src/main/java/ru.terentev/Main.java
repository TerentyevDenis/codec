package ru.terentev;

public class Main {
    public static void main(String[] args){
        for (int i = 0; i < args.length; i++) {
            switch (args[i]){
                case "--keygen":{
                    Keygen.generateKey();
                    break;
                }
                case "--encrypt":{
                    Encoder.encryptDecryptFile(args[++i], Keygen.getKey("public.key"));
                    break;
                }

                case "--decrypt":{
                    Encoder.encryptDecryptFile(args[++i], Keygen.getKey("private.key"));
                    break;
                }

                case "--sign":{
                    Encoder.signFile(args[++i], Keygen.getKey("private.key"));
                    break;
                }

                case "--check":{
                    Encoder.checkFile(args[++i], Keygen.getKey("public.key"));
                    break;
                }
                case "--help":{
                    System.out.println(" --keygen : generate public and private keys\n"+
                            " --encrypt <file> : encrypt given file using existing keys\n"+
                            " --decrypt <file> : decrypt given file using existing keys\n"+
                            " --sign <file> : generate electronic sign for given file using private key\n"+
                            " --check <file> : check file using existing sign and public key\n");
                    break;
                }

                default:{
                    System.out.println("Command not recognised");
                    i = args.length;
                }
            }
        }
    }
}
