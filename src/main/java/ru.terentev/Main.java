package ru.terentev;

public class Main {
    public static void main(String[] args){
        for (int i = 0; i < args.length; i++) {
            switch (args[i]){
                case "--keygen":{
                    Keygen.generateKey();
                    break;
                }
                case "--incript":{
                    Encoder.incriptDecriptFile(args[++i], Keygen.getKey("public.key"));
                    break;
                }

                case "--decript":{
                    Encoder.incriptDecriptFile(args[++i], Keygen.getKey("private.key"));
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

                default:{
                    System.out.println("Command not recognised");
                }
            }
        }
    }
}
