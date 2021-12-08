package dt.pilot.shared.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RedisTopicEnum {
  ENTITY_CREATED("entity.created"), ENTITY_DELETED("entity.deleted"), ENTITY_UPDATED(
      "entity.updated");

  private final String topic;

  @JsonValue
  public String getTopic() {
    return topic;
  }

  RedisTopicEnum(String topic) {
    this.topic = topic;
  }

  @JsonCreator
  public static RedisTopicEnum fromText(String text) {
    for (RedisTopicEnum v : values()) {
      if (v.topic.equals(text.toLowerCase())) {
        return v;
      }
    }
    return null;
  }

}
