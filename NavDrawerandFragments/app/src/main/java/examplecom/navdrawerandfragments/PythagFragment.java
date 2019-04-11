package examplecom.navdrawerandfragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PythagFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //inflate our view (make java objects from the xml)
        View rootView = inflater.inflate(R.layout.fragment_pythag, container, false);

        //wire widgets & set listeners
        //the rootView has the findViewById in it
        TextView tv = rootView.findViewById(R.id.textView);
        tv.setText("Hi. I inflated a view.");

        return rootView;
    }
}
