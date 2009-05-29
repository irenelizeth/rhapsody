/*
 * U2UDownloadNode.fx
 *
 * Created on 19-may-2009, 16:27:40
 */

package org.u2u.data;

import org.u2u.filesharing.U2UContentAdvertisementImpl;

/**
 * @author sergio
 */

public def DOWNLOAD:String = "download";
public def PAUSE:String = "pause";
public def OK:String = "ok";

public class U2UDownloadNode extends U2UAbstractNode {

    /** level of download of this node [max:200- min:0] */
    public-read var level:Float on replace {
        println("the level changet to {level}");
        if(level == 200)
        {
            this.status = U2UDownloadNode.OK;
        }
    };
    /**status for this file: DOWNLOAD or PAUSE*/
    public-read var status:String = U2UDownloadNode.DOWNLOAD;

    //instance methods
    override function getName(): String {
        return this.name;
    }

    /** return the cid in string format*/
    override function getCID(): String {
        return this.cid.toString();
    }

    /** return the sharedfile's length in bytes*/
    override function getLength(): Long {
        return this.length;
    }

    /** return the sharedfile's description*/
    override function getDescription(): String {
        return this.description;
    }

    override function getType(): String {
        return this.type;
    }

    /** chunk's size in kilobytes*/
    override function getChunksize(): Short {
        return this.chunksize;
    }

    override function getAdv(): U2UContentAdvertisementImpl {
        return this.adv
    }

    override function getShellEnv(): String {
        return this.shellEnv;
    }



    /**
    * Change the level of download of the node
    */
    public function updateLevel(lev:Float):Void {
        
        if(lev>=0 and lev<=200 )
        {
            this.level = lev;
        }
    }

    /**
    * Set the estatus of this file: DOWNLOAD or PAUSE
    */
    public function setStatus(status:String):Void{

        if(status.equals(U2UDownloadNode.DOWNLOAD)
            or status.equals(U2UDownloadNode.PAUSE) )
        {
            this.status = status;
        }

    }

//    function getTypeFile():Image {
//
//        var nam:String = this.name as String;
//        var type:String= TypeFile.getTypeFile(nam.substring(nam.indexOf('.')+1));
//
//        var imgType:Image = TypeFile.getImageTypeFile(type);
//
//        return imgType;
//    }



}

