package org.betastudio.application.utils.actions;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

/**
 * 一个非常好用的计时器
 */
public class Timer {
    public double StartTime,EndTime;
    public final Map<String, Double> Tags;
    public final Map<String, Object> TagMeaning;
    public final Map<String, Vector<Double>> MileageTags;

    public Timer(){
	    this.StartTime = getCurrentTime();
	    this.Tags =new HashMap<>();
	    this.TagMeaning =new HashMap<>();
	    this.MileageTags =new HashMap<>();
    }

    public static double getCurrentTime(){
        return System.nanoTime()/1e6;
    }

    /**重新定义{@code StartTime}*/
    public final void restart(){
	    this.StartTime = getCurrentTime();
    }
    /**定义{@code EndTime}*/
    public final void stop(){
	    this.EndTime = getCurrentTime();
    }
    /**获取{@code EndTime-StartTime}*/
    public final double getDeltaTime(){
        return this.EndTime - this.StartTime;
    }
    /**定义{@code EndTime}并获取{@code EndTime-StartTime}*/
    public final double stopAndGetDeltaTime(){
	    this.stop();
        return this.getDeltaTime();
    }
    /**定义{@code EndTime}并获取{@code EndTime-StartTime}并重新定义{@code StartTime}*/
    public final double restartAndGetDeltaTime(){
        final double res = this.stopAndGetDeltaTime();
	    this.restart();
        return res;
    }
    /**重新定义{@code StartTime}并定义{@code EndTime}*/
    public final void stopAndRestart(){
	    this.stop();
	    this.restart();
    }

    /**自动覆写如果存在相同的tag*/
    public final void pushTimeTag(final String tag){
        if(this.Tags.containsKey(tag)){
	        this.Tags.replace(tag, getCurrentTime());
        }else {
	        this.Tags.put(tag, getCurrentTime());
        }
    }
    /**自动覆写如果存在相同的tag*/
    public final void pushObjectionTimeTag(final String tag, final Object objection){
	    this.pushTimeTag(tag);
        if(this.Tags.containsKey(tag)){
	        this.TagMeaning.replace(tag,objection);
        }else {
	        this.TagMeaning.put(tag,objection);
        }
    }

    /**
     * @return 未申明時返回0
     */
    public double getTimeTag(final String tag){
        final Double v = this.Tags.get(tag);
        return null == v ? 0 : v;
    }
    /**
     * @return 未申明時返回0
     */
    public Object getTimeTagObjection(final String tag){
        final Object v = this.TagMeaning.get(tag);
        return null == v ? 0 : v;
    }

    public void pushMileageTimeTag(final String tag){
	    this.pushTimeTag(tag);
        if (this.MileageTags.containsKey(tag)){
            Objects.requireNonNull(this.MileageTags.get(tag)).add(getCurrentTime());
        }else{
            final Vector<Double> cache =new Vector<>();
            cache.add(getCurrentTime());
	        this.MileageTags.put(tag,cache);
        }
    }
    public Vector<Double> getMileageTimeTag(final String tag){
        return this.MileageTags.get(tag);
    }
}