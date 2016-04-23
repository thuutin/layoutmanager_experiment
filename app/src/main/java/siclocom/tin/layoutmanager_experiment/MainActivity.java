package siclocom.tin.layoutmanager_experiment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  private RecyclerView view;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    view = (RecyclerView) findViewById(R.id.recycler_view);
    Button b = (Button) findViewById(R.id.button);
    b.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        view.setLayoutManager(new MyLayoutManager());
      }
    });
    view.setAdapter(new MyAdapter());
  }

  @Override protected void onPostResume() {
    super.onPostResume();
  }
}
