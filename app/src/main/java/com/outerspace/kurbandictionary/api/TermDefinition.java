
package com.outerspace.kurbandictionary.api;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@Entity
public class TermDefinition {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @SerializedName("definition")
    @Expose
    public String definition;   /* text definition of the urban term */
    @SerializedName("permalink")
    @Expose
    public String permalink;    /* url to this definition. (uses defid) */
    @SerializedName("thumbs_up")
    @Expose
    public Integer thumbsUp;    /* count of thumbs up (likes) */

    @Ignore
    @SerializedName("sound_urls")
    @Expose
    public java.util.List<String> soundUrls = new ArrayList<String>();  /* List of URL to wav sound files */
    @SerializedName("author")
    @Expose
    public String author;       /* name of the author */
    @SerializedName("word")
    @Expose
    public String word;         /* the term corresponding to this definition (original fetched for) */
    @SerializedName("defid")
    @Expose
    public Integer defid;       /* definition id (used in permalink) */
    @SerializedName("current_vote")
    @Expose
    public String currentVote;  /* do no */
    @SerializedName("written_on")
    @Expose
    public String writtenOn;    /* date the term was written. */
    @SerializedName("example")
    @Expose
    public String example;      /* text with some markup: bold text with [] and \r\n is a new line. <...> are just user names */
    @SerializedName("thumbs_down")
    @Expose
    public Integer thumbsDown;  /* count of thumbs down (dislikes) */

}
