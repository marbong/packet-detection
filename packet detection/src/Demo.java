import java.awt.Color;
import java.util.LinkedList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class Demo extends ApplicationFrame {
	LinkedList List = new LinkedList();
	LinkedList List2 = new LinkedList();
	final static String title = "Packet Data";
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	public Demo(){
		super(title);
	}
	public void DualAxisDemoSet(int count,int size, int countGraph) {

		

		final CategoryDataset dataset1 = createDataset1(count,countGraph);

		// 차트생성
		final JFreeChart chart = ChartFactory.createBarChart("Packet Data", // 차트 제목
				"Packet", // X축제목
				"Packet Count", // 왼쪽 Y축제목
				dataset1, // data
				PlotOrientation.VERTICAL, false, // 움직이면 그자리에 있도록 false
				true, // tooltips?
				false // URL generator? Not required...
				);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.setBackgroundPaint(Color.white);
		
		// chart.getLegend().setAnchor(Legend.SOUTH);

		// get a reference to the plot for further customisation...
		final CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
		plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);

		final CategoryDataset dataset2 = createDataset2(size,countGraph);
		plot.setDataset(1, dataset2);
		plot.mapDatasetToRangeAxis(1, 1);

		final CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
		final ValueAxis axis2 = new NumberAxis("Packet size");	//오른쪽 Y축제목
		plot.setRangeAxis(1, axis2);

		final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
		renderer2.setToolTipGenerator(new StandardCategoryToolTipGenerator());
		plot.setRenderer(1, renderer2);
		plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
		// OPTIONAL CUSTOMISATION COMPLETED.

		// panel 크기
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 400));
		setContentPane(chartPanel);

	}


	
	private CategoryDataset createDataset1(int size, int counts) {
		List.add(size);
		//counts++;
		// row keys...
		final String series1 = "Packet Size";

		// column keys...
		
		
		// create the dataset...
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(int a = 0;a<counts;a++)
		dataset.addValue((Number) List.get(a), series1, "second"+a);

		return dataset;

	}

	private CategoryDataset createDataset2(int count, int counts) {
		List2.add(count);
		// row keys...
		final String series1 = "Packet Count";

		// column keys...

		// create the dataset...
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for(int a = 0;a<counts;a++)
		dataset.addValue((Number)List2.get(a), series1, "second"+a);


		return dataset;

	}

	

}