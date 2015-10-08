package learnJava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author Petercao
 * Version 1.0.0
 */
public class ChangeEncode {
  String datasorce = "C:\\Users\\tzhang\\Documents\\Github_LeetLint\\leetlint\\freq1_tony";
  String tagString = "C:\\Users\\tzhang\\Documents\\Github_LeetLint\\leetlint\\utf";

 // ת���ı��ĵ��ı����ʽ��
 public ChangeEncode() {
  // Ҫ����ԭ�ļ���
  File source = new File(datasorce);
  File[] file = source.listFiles();
  System.out.println(file[0].getName());
  FileInputStream fileInputStream = null;
  InputStreamReader inputStreamReader = null;
  BufferedReader bufferedReader = null;
  // Ҫд������ļ���
  File tag = new File(tagString);
  String[] nameString = source.list();
  FileOutputStream fileOutputStream = null;
  OutputStreamWriter outputStreamWriter = null;
  BufferedWriter bufferedWriter = null;
  for (int i = 0; i < file.length; i++) {
   try {
    // Ҫ����ԭ�ļ���
    fileInputStream = new FileInputStream(file[i]);
    try {

     // ԭ�����ı��ı����ʽ��gb2312
     inputStreamReader = new InputStreamReader(fileInputStream, "utf8");
    } catch (UnsupportedEncodingException e3) {
     // TODO Auto-generated catch block
     e3.printStackTrace();
    }
    bufferedReader = new BufferedReader(inputStreamReader);
    // Ҫд������ļ���
    fileOutputStream = new FileOutputStream(tagString + File.separator + nameString[i]);
    try {

     // ת������ı��ı����ʽ��utf-8��
     outputStreamWriter = new OutputStreamWriter(fileOutputStream, "gb2312");
    } catch (UnsupportedEncodingException e2) {
     // TODO Auto-generated catch block
     e2.printStackTrace();
    }
    bufferedWriter = new BufferedWriter(outputStreamWriter);
    String line = bufferedReader.readLine();
    try {
     while (line != null) {
      bufferedWriter.write(line);
      line = bufferedReader.readLine();
     }
    } catch (IOException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
    }
    try {
     bufferedWriter.close();
     outputStreamWriter.close();
     fileOutputStream.close();

    } catch (IOException e1) {
     // TODO Auto-generated catch block
     e1.printStackTrace();
    }
    try {
     bufferedReader.close();
     inputStreamReader.close();
     fileInputStream.close();

    } catch (IOException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
    }

   } catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
  }
 }

 public static void main(String[] args) {
  ChangeEncode changeEncode = new ChangeEncode();
 }
} 