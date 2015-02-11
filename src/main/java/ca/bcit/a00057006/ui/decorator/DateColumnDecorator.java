package ca.bcit.a00057006.ui.decorator;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import javax.servlet.jsp.PageContext;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Basic column decorator to 'pretty' the date format of a column in a displayTag table
 *
 * @author Mark Doucette
 */
public class DateColumnDecorator implements DisplaytagColumnDecorator {
    public final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy / MM / dd");

    @Override
    public Object decorate(Object columnVal, PageContext pageContext, MediaTypeEnum mediaTypeEnum) throws DecoratorException {
        Date date = (Date)columnVal;

        return this.DATE_FORMAT.format(date);
    }
}
