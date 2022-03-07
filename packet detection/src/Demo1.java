import java.awt.BorderLayout;
import java.awt.Color;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Demo1 extends ApplicationFrame {

	public static final int SUBPLOT_COUNT = 2;

	private TimeSeriesCollection[] datasets;

	private int[] lastValue = new int[SUBPLOT_COUNT];

	public Demo1() {

		super("Packet");

		final CombinedDomainXYPlot plot = new CombinedDomainXYPlot(
				new DateAxis("Time"));
		this.datasets = new TimeSeriesCollection[SUBPLOT_COUNT];

	

		
			final TimeSeries series = new TimeSeries("패킷의 갯수",Millisecond.class);
			this.datasets[1] = new TimeSeriesCollection(series);

	
				final NumberAxis rangeAxis = new NumberAxis("Packet Count");
				rangeAxis.setAutoRangeIncludesZero(false); // y축 변화
				final XYPlot subplot = new XYPlot(this.datasets[1], null, rangeAxis, new StandardXYItemRenderer()); // 차트
				subplot.setBackgroundPaint(Color.lightGray);
				subplot.setDomainGridlinePaint(Color.white);
				subplot.setRangeGridlinePaint(Color.white);
				plot.add(subplot);
			
		

		final JFreeChart chart = new JFreeChart("Packet", plot); // 차트이름

		chart.setBorderPaint(Color.black);
		chart.setBorderVisible(true);
		chart.setBackgroundPaint(Color.white);

		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		final ValueAxis axis = plot.getDomainAxis();
		
		axis.setAutoRange(true); // x축 변화
	    axis.setFixedAutoRange(10000.0); // 60 seconds
	
		final JPanel content = new JPanel(new BorderLayout());

		final ChartPanel chartPanel = new ChartPanel(chart);
		content.add(chartPanel);

		chartPanel.setPreferredSize(new java.awt.Dimension(800, 400));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setContentPane(content);

	}

	public void ShowData(int sum) {

	
				this.lastValue[1] = sum;
			
			this.datasets[1].getSeries(0).add(new Millisecond(),this.lastValue[1]);
		
	}
}