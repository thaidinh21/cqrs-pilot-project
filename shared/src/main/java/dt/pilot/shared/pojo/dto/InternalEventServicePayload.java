package dt.pilot.shared.pojo.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class InternalEventServicePayload implements Serializable {


  private static final long serialVersionUID = 1L;
  private String clazzType; // full qualified name of class
  private Object payload;
}
