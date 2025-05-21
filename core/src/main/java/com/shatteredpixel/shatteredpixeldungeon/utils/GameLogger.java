package com.shatteredpixel.shatteredpixeldungeon.utils;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class GameLogger {
    private static BufferedWriter writer;
    static {
        try {
            // 以追加模式打开日志文件
            writer = new BufferedWriter(new FileWriter("logfile.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 获取当前时间的时间戳字符串
    private static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    // 通用日志写入方法：加上时间戳和类型后写入控制台和文件
    private static void writeLog(String type, String detail) {
        String entry = timestamp() + " [" + type + "] " + detail;
        System.out.println(entry);
        try {
            writer.write(entry);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 日志类型1：怪物生成
    public static void logSpawn(Mob mob) {
        writeLog("Mob Spawn", String.format("MobID=%d Name=%s spawned",
                mob.getId(), mob.getClass().getSimpleName()));
    }
    // 日志类型2：状态切换
    public static void logStateTransition(Mob mob, String oldState, String newState) {
        writeLog("State Transition", String.format("MobID=%d Name=%s state changed from %s to %s",
                mob.getId(), mob.getClass().getSimpleName(), oldState, newState));
    }
    // 日志类型3：警觉状态
    public static void logAlert(Mob mob) {
        writeLog("Alerted", String.format("MobID=%d Name=%s became ALERTED",
                mob.getId(), mob.getClass().getSimpleName()));
    }
    // 日志类型4：目标设置
    public static void logTargetAssignment(Mob mob, String targetDesc) {
        writeLog("Target Assignment", String.format("MobID=%d Name=%s target set to %s",
                mob.getId(), mob.getClass().getSimpleName(), targetDesc));
    }
}
