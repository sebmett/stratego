
package stratego;

import java.applet.Applet ;
import java.applet.AudioClip ;
import java.net.URL ;
import java.net.MalformedURLException ;

public class Sons
    {
    private URL fichier ;
    private AudioClip son ;

    public Sons(String name)
        {
        try
           {
           fichier = new URL(name) ;
           son = Applet.newAudioClip(fichier) ;
           }
        catch (MalformedURLException e)
           {
           System.err.println("Fichier selectionne introuvable") ;
           }
        }

    public URL getFile()
        {
        return fichier ;
        }

    public AudioClip getSound()
        {
        return son ;
        }

    public void setFile(String name)
        {
        try
          {
          fichier = new URL(name) ;
          }
        catch (MalformedURLException e)
          {
          System.err.println("Fichier selectionne introuvable") ;
          }
        }

    public void setSound(URL file)
        {
        son = Applet.newAudioClip(file) ;
        }

    public void playSound()
        {
    	son.play() ;
        }

    public void loopSound()
        {
    	son.loop() ;
        }

    public void stopSound()
       {
    	son.stop() ;
       }
    }